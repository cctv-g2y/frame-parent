package com.makun.service.workflow.impl;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.makun.entity.workflow.Creatorconfine;
import com.makun.service.workflow.ActModelService;
import com.makun.service.workflow.CreatorconfineService;
import com.makun.utils.DateUtils;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;
import com.makun.utils.StringUtils;

/**
 * @说明:流程模型服务层
 * @author makun
 */
@Service
public class ActModelServiceImpl implements ActModelService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private CreatorconfineService creatorconfineService;

    protected ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @说明:流程模型列表数据
     */
    @Override
    public Map<String, Object> modelList(QueryUtil query) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        String search = (String) query.get("search");
        ModelQuery modelQuery = repositoryService.createModelQuery().latestVersion().orderByLastUpdateTime().desc();
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            // 按照模型名称模糊查询
            search = "%" + search + "%";
            modelQuery = modelQuery.modelNameLike(search);
        }
        resultMap.put("total", modelQuery.count());
        List<Model> listPage = modelQuery.listPage(query.getOffset(), query.getLimit());
        for (Model model : listPage) {
            String modelId = model.getId();
            Map<String, Object> maps = new HashMap<String, Object>();
            Creatorconfine creatorconfine = creatorconfineService.getByModelId(modelId);
            maps.put("id", modelId);
            maps.put("key", model.getKey());
            maps.put("name", model.getName());
            maps.put("category", model.getCategory());
            maps.put("createTime", DateUtils.format(model.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("lastUpdateTime", DateUtils.format(model.getLastUpdateTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("version", model.getVersion());
            maps.put("isConfig", null == creatorconfine ? '1' : '0');
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明:创建模型
     */
    @Override
    @Transactional
    @SuppressWarnings("deprecation")
    public Model createModel(String name, String key, String description, String category)
            throws UnsupportedEncodingException {
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode properties = objectMapper.createObjectNode();
        properties.put("process_author", "jeesite");
        editorNode.put("properties", properties);
        ObjectNode stencilset = objectMapper.createObjectNode();
        stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilset);

        Model modelData = repositoryService.newModel();
        description = StringUtils.defaultString(description);
        modelData.setKey(StringUtils.defaultString(key));
        modelData.setName(name);
        modelData.setCategory(category);
        modelData.setVersion(Integer.parseInt(
                String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count() + 1)));

        ObjectNode modelObjectNode = objectMapper.createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelData.setMetaInfo(modelObjectNode.toString());

        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
        return modelData;
    }

    /**
     * @说明:删除模型
     */
    @Override
    @Transactional
    public R delete(String[] ids) {
        if (0 < ids.length) {
            for (String id : ids) {
                repositoryService.deleteModel(id);
                creatorconfineService.delByModelId(id);
            }
        }
        return R.ok("删除成功");
    }

    /**
     * @说明:导出model的xml文件
     */
    @Override
    public R exports(String id, HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(id);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
            if (null == modelEditorSource) {
                return R.error("模型数据为空，请先设计流程并成功保存，再进行导出，模型ID=" + id);
            }
            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            if (0 == bpmnModel.getProcesses().size()) {
                return R.error("数据模型不符要求，请至少设计一条主线流程");
            }
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
            return R.ok("导出model的xml文件成功，模型ID=" + id);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("导出model的xml文件失败，模型ID=" + id);
        }
    }

    /**
     * @说明:根据Model部署流程
     */
    @Override
    @Transactional
    public R deploys(String modelId, String userId) {
        Creatorconfine creatorconfine = creatorconfineService.getByModelId(modelId);
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());
            if (null == modelEditorSource) {
                return R.error("模型数据为空，请先设计流程并成功保存，再进行发布");
            }
            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            if (0 == bpmnModel.getProcesses().size()) {
                return R.error("数据模型不符要求，请至少设计一条主线流程");
            }
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            String processName = modelData.getName();
            if (!StringUtils.endsWith(processName, ".bpmn20.xml")) {
                processName += ".bpmn20.xml";
            }
            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
                    .addInputStream(processName, in).deploy();
            // 设置流程分类
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId()).list();
            for (ProcessDefinition processDefinition : list) {
                repositoryService.setProcessDefinitionCategory(processDefinition.getId(), modelData.getCategory());
                if (null != creatorconfine) {
                    String key = processDefinition.getKey();
                    String mkey = creatorconfine.getProcessKey();
                    // mkey为空表示第一次部署，或者俩个key相等表示非第一次部署，但是没有修改key
                    if (StringUtils.isBlank(mkey) || key.equals(mkey)) {
                        creatorconfine.setProcessKey(processDefinition.getKey());
                        creatorconfine.setUpdateId(userId);
                        creatorconfine.setUpdateTime(new Date());
                        creatorconfine.setDelFlag("1");
                        creatorconfineService.updateById(creatorconfine);
                    } else {
                        // mkey非空且与之前不相等表示修改了流程的key，此时需要添加一条记录与之对应
                        creatorconfine.setId(StringUtils.getUUID());
                        creatorconfine.setProcessKey(processDefinition.getKey());
                        creatorconfine.setCreateId(userId);
                        creatorconfine.setCreateTime(new Date());
                        creatorconfine.setUpdateId(userId);
                        creatorconfine.setUpdateTime(new Date());
                        creatorconfine.setDelFlag("1");
                        creatorconfineService.insert(creatorconfine);
                    }
                }
                return R.ok("部署成功，流程ID=" + processDefinition.getId());
            }
            if (list.size() == 0) {
                return R.error("部署失败，没有流程");
            }
        } catch (Exception e) {
            throw new ActivitiException("设计模型图不正确，检查模型正确性，模型ID=" + modelId, e);
        }
        return R.error("设计模型图不正确，检查模型正确性，模型ID=" + modelId);
    }

    /**
     * @说明:改变流程分类
     */
    @Override
    public R updateCategory(String modelId, String category) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            modelData.setCategory(category);
            repositoryService.saveModel(modelData);
            return R.ok("更改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("更改失败");
        }
    }

}
