package com.makun.controller.workflow;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.makun.date.Constants;
import com.makun.entity.system.SysDict;
import com.makun.service.system.SysDictService;
import com.makun.service.workflow.ActProcessService;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;

/**
 * @说明:工作流定义控制层
 * @author makun
 */
@RestController
@RequestMapping("actprocess")
public class ActProcessController {

    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private SysDictService sysDictservice;

    /**
     * @说明:定义列表页面
     */
    @RequestMapping("processlistpage")
    public ModelAndView processListPage() {
        return new ModelAndView("modules/workflow/processlist");
    }

    /**
     * @说明:定义列表数据
     */
    @RequestMapping("listdata")
    public Map<String, Object> listData(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return actProcessService.processList(query);
    }

    /**
     * @说明:运行中的实例列表数据
     */
    @RequestMapping("running")
    public Map<String, Object> runningList(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return actProcessService.runningList(query);
    }

    /**
     * @说明:通过部署ID读取资源
     * @param processDefinitionId 流程定义ID
     * @param processInstanceId   流程实例ID
     * @param resourceType        资源类型(xml|image)
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "resource/read")
    public void resourceRead(String procDefId, String proInsId, String resType, HttpServletResponse response)
            throws Exception {
        InputStream resourceAsStream = actProcessService.resourceRead(procDefId, proInsId, resType);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }

    /**
     * @说明:部署流程 - 保存
     */
    @RequestMapping("deploy")
    public R deploy(@Value("#{APP_PROP['activitiPath']}") String exportDir, String category,
            @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            return R.error("请选择要部署的流程文件");
        } else {
            return actProcessService.deploys(exportDir, category, file);
        }
    }

    /**
     * @说明:挂起、激活流程实例
     */
    @RequestMapping(value = "update/{state}")
    public R updateState(@PathVariable("state") String state, String procDefId) {
        return actProcessService.updateState(state, procDefId);
    }

    /**
     * @说明:将部署的流程转换为模型
     */
    @RequestMapping(value = "convert/tomodel")
    public R convertToModel(String procDefId) throws UnsupportedEncodingException, XMLStreamException {
        return actProcessService.convertToModel(procDefId);
    }

    /**
     * @说明:导出图片文件到硬盘
     */
    @RequestMapping(value = "export/diagrams")
    public R exportDiagrams(@Value("#{APP_PROP['activitiPath']}") String exportDir, String procDefId)
            throws IOException {
        List<String> exportDiagrams = actProcessService.exportDiagrams(exportDir, procDefId);
        if (null != exportDiagrams && !exportDiagrams.isEmpty()) {
            return R.ok("导出成功");
        } else {
            return R.error("导出失败");
        }
    }

    /**
     * @说明:删除部署的流程，级联删除流程实例
     * @param deploymentId 流程部署ID
     */
    @RequestMapping("delete")
    public R delete(@RequestBody String[] deploymentIds) {
        return actProcessService.deleteDeployment(deploymentIds);
    }

    /**
     * @说明:删除流程实例
     * @param procInsId 流程实例ID
     * @param reason    删除原因
     */
    @RequestMapping("deleteprocins")
    public R deleteProcIns(@RequestBody String[] procInsIds, String reason) {
        reason = "删除该流程实例";
        if (StringUtils.isBlank(reason)) {
            return R.error("请填写删除原因");
        } else {
            return actProcessService.deleteProcIns(procInsIds, reason);
        }
    }

    /**
     * @说明:获取流程分类
     */
    @RequestMapping("getprocesscategory")
    public List<SysDict> getModelCategory() {
        return sysDictservice.findListByPcode(Constants.ACT_CODE);
    }

    /**
     * @说明:更新流程分类--暂未用到
     */
    @RequestMapping(value = "updatecategory")
    public R updateCategory(String id, String category) {
        return actProcessService.updateCategory(id, category);
    }

}
