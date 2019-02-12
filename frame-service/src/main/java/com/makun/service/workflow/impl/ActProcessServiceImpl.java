package com.makun.service.workflow.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.makun.service.workflow.ActProcessService;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;

/**
 * @说明:流程定义服务层
 * @author makun
 */
@Service
public class ActProcessServiceImpl implements ActProcessService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * @说明:日志对象
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @说明:定义列表数据
     */
    @Override
    public Map<String, Object> processList(QueryUtil query) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        String search = (String) query.get("search");
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().latestVersion()
                .orderByProcessDefinitionKey().asc();
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            search = "%" + search + "%";
            // 按照流程名称查询
            processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike(search);
        }
        resultMap.put("total", processDefinitionQuery.count());
        List<ProcessDefinition> listPage = processDefinitionQuery.listPage(query.getOffset(), query.getLimit());
        // 保存两个对象，一个是ProcessDefinition（流程定义），一个是Deployment（流程部署）
        for (ProcessDefinition processDefinition : listPage) {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("category", processDefinition.getCategory());
            maps.put("id", processDefinition.getId());
            maps.put("key", processDefinition.getKey());
            maps.put("name", processDefinition.getName());
            maps.put("version", processDefinition.getVersion());
            maps.put("deploymentId", deploymentId);
            maps.put("deploymentTime", deployment.getDeploymentTime());
            maps.put("isSuspended", processDefinition.isSuspended());
            maps.put("xmlName", processDefinition.getResourceName());
            maps.put("imageName", processDefinition.getDiagramResourceName());
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明:运行中的实例列表数据
     */
    @Override
    public Map<String, Object> runningList(QueryUtil query) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        String search = (String) query.get("search");
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            // 按照流程定义key查询
            processInstanceQuery = processInstanceQuery.processInstanceNameLike(search);
        }
        resultMap.put("total", processInstanceQuery.count());
        List<ProcessInstance> listPage = processInstanceQuery.listPage(query.getOffset(), query.getLimit());
        for (ProcessInstance processInstance : listPage) {
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("id", processInstance.getId());
            maps.put("processInstanceId", processInstance.getProcessInstanceId());
            maps.put("processDefinitionId", processInstance.getProcessDefinitionId());
            maps.put("activityId", processInstance.getActivityId());
            maps.put("isSuspended", processInstance.isSuspended());
            maps.put("processDefinitionName", processInstance.getProcessDefinitionName());
            maps.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
            maps.put("processDefinitionVersion", processInstance.getProcessDefinitionVersion());
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明:通过部署ID读取资源
     * @param processDefinitionId 流程定义ID
     * @param processInstanceId   流程实例ID
     * @param resourceType        资源类型(xml|image)
     */
    @Override
    public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception {
        if (StringUtils.isBlank(procDefId)) {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId)
                    .singleResult();
            procDefId = processInstance.getProcessDefinitionId();
        }
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(procDefId).singleResult();
        String resourceName = "";
        if ("image".equals(resType)) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if ("xml".equals(resType)) {
            resourceName = processDefinition.getResourceName();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                resourceName);
        return resourceAsStream;
    }

    /**
     * @说明:部署流程 - 保存
     */
    @Override
    public R deploys(String exportDir, String category, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            InputStream fileInputStream = file.getInputStream();
            Deployment deployment;
            String extension = FilenameUtils.getExtension(fileName);
            if ("zip".equals(extension) || "bar".equals(extension)) {
                ZipInputStream zip = new ZipInputStream(fileInputStream);
                deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
            } else if ("png".equals(extension)) {
                deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
            } else if (fileName.indexOf("bpmn20.xml") != -1) {
                deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
            } else if ("bpmn".equals(extension)) { // bpmn扩展名特殊处理，转换为bpmn20.xml
                String baseName = FilenameUtils.getBaseName(fileName);
                deployment = repositoryService.createDeployment()
                        .addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
            } else {
                return R.error("不支持的文件类型：" + extension);
            }
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId()).list();
            // 设置流程分类
            for (ProcessDefinition processDefinition : list) {
                repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
                return R.ok("部署成功，流程ID=" + processDefinition.getId());
            }

            if (list.size() == 0) {
                return R.error("部署失败，没有流程");
            }
        } catch (Exception e) {
            throw new ActivitiException("部署失败", e);
        }
        return R.error("部署失败");
    }

    /**
     * @说明:设置流程分类
     */
    @Override
    @Transactional
    public R updateCategory(String procDefId, String category) {
        try {
            repositoryService.setProcessDefinitionCategory(procDefId, category);
            return R.ok("更改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("更改失败");
        }
    }

    /**
     * @说明:挂起、激活流程实例
     */
    @Override
    @Transactional
    public R updateState(String state, String procDefId) {
        if ("active".equals(state)) {
            repositoryService.activateProcessDefinitionById(procDefId, true, null);
            return R.ok("已激活ID为[" + procDefId + "]的流程定义。");
        } else if ("suspend".equals(state)) {
            repositoryService.suspendProcessDefinitionById(procDefId, true, null);
            return R.ok("已挂起ID为[" + procDefId + "]的流程定义。");
        }
        return R.error("无操作");
    }

    /**
     * @说明:将部署的流程转换为模型
     */
    @Override
    public R convertToModel(String procDefId) throws UnsupportedEncodingException, XMLStreamException {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(procDefId).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode modelNode = converter.convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getResourceName());
        modelData.setCategory(processDefinition.getCategory());// .getDeploymentId());
        modelData.setDeploymentId(processDefinition.getDeploymentId());
        modelData.setVersion(Integer.parseInt(
                String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count() + 1)));
        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());
        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
        return R.ok("转换模型成功，模型ID=" + modelData.getId());
    }

    /**
     * @说明:导出图片文件到硬盘
     */
    @Override
    public List<String> exportDiagrams(String exportDir, String procDefId) throws IOException {
        List<String> files = new ArrayList<String>();
        List<ProcessDefinition> list = new ArrayList<ProcessDefinition>();
        if (null != procDefId && !"".equals(procDefId)) {
            list = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).list();
        } else {
            list = repositoryService.createProcessDefinitionQuery().list();
        }
        for (ProcessDefinition processDefinition : list) {
            String diagramResourceName = processDefinition.getDiagramResourceName();
            String key = processDefinition.getKey();
            int version = processDefinition.getVersion();
            String diagramPath = "";
            InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                    diagramResourceName);
            byte[] b = new byte[resourceAsStream.available()];
            @SuppressWarnings("unused")
            int len = -1;
            resourceAsStream.read(b, 0, b.length);
            // create file if not exist
            String diagramDir = exportDir + "/" + key + "/" + version;
            File diagramDirFile = new File(diagramDir);
            if (!diagramDirFile.exists()) {
                diagramDirFile.mkdirs();
            }
            diagramPath = diagramDir + "/" + diagramResourceName;
            File file = new File(diagramPath);
            // 文件存在退出
            if (file.exists()) {
                // 文件大小相同时直接返回否则重新创建文件(可能损坏)
                logger.debug("diagram exist, ignore... : {}", diagramPath);
                files.add(diagramPath);
            } else {
                file.createNewFile();
                logger.debug("export diagram to : {}", diagramPath);
                // wirte bytes to file
                FileUtils.writeByteArrayToFile(file, b, true);
                files.add(diagramPath);
            }
        }
        return files;
    }

    /**
     * @说明:删除部署的流程，级联删除流程实例
     */
    @Override
    @Transactional
    public R deleteDeployment(String[] deploymentIds) {
        // 传参,部署id和一个Boolean字段,true-表示级联删除;false-表示不级联删除
        for (String deploymentId : deploymentIds) {
            repositoryService.deleteDeployment(deploymentId, true);
        }
        return R.ok("删除成功");
    }

    /**
     * @说明:删除部署的流程实例
     */
    @Override
    @Transactional
    public R deleteProcIns(String[] procInsIds, String deleteReason) {
        for (String procInsId : procInsIds) {
            runtimeService.deleteProcessInstance(procInsId, deleteReason);
        }
        return R.ok("删除流程实例成功，实例ID=" + procInsIds);
    }

}
