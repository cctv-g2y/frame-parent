package com.makun.controller.worktask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.makun.config.base.UserUtils;
import com.makun.entity.workflow.Creatorconfine;
import com.makun.service.system.SysUserRoleService;
import com.makun.service.workflow.CreatorconfineService;
import com.makun.service.worktask.WorkTaskService;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;
import com.makun.utils.StringUtils;

/**
 * @说明:流程个人任务相关Controller
 */
@RestController
@RequestMapping("work/task")
public class WorkTaskController {

    @Autowired
    private WorkTaskService workTaskService;
    
    @Autowired
    private CreatorconfineService creatorconfineService;
    
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * @说明:发起流程列表页面
     */
    @RequestMapping("startworklistpage")
    public ModelAndView startWorkListPage() {
        return new ModelAndView("modules/worktask/startworklist");
    }

    /**
     * @说明:待发界面获取流程列表数据
     */
    @RequestMapping("processdata")
    public Map<String, Object> processList(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return workTaskService.processList(query);
    }

    /**
     * @说明:获取已发任务列表数据
     */
    @RequestMapping("sentdata")
    public Map<String, Object> sentList(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return workTaskService.sentList(query, UserUtils.getUserId());
    }

    /**
     * @说明：启动流程页面
     */
    @RequestMapping("startpage")
    public ModelAndView startWorkFlowPage(String processKey, String procDefId, String procDefName) {
        Creatorconfine creatorconfine = creatorconfineService.getByDefKey(processKey);
        if (null != creatorconfine) {
            String userId = UserUtils.getUserId();
            int isOk = 0; // 验证当前用户是否拥有发起该流程的权限-0.表示未拥有;1.表示已拥有
            List<String> myRoleList = sysUserRoleService.queryRoleIdList(userId);
            String userIds = creatorconfine.getUserIds();
            String roleIds = creatorconfine.getRoleIds();
            List<String> userList = new ArrayList<String>();
            List<String> roleList = new ArrayList<String>();
            if (StringUtils.isNotBlank(userIds)) {
                userList = Arrays.asList(userIds.split(","));
            }
            if (StringUtils.isNotBlank(roleIds)) {
                roleList = Arrays.asList(roleIds.split(","));
            }
            // 验证用户
            if (userList.contains(userId)) {
                isOk = 1;
            }
            // 用户权限验证未通过且如果当前登录人角色不为空，则验证角色
            if (1 != isOk && null != myRoleList && !myRoleList.isEmpty()) {
                for (String string : myRoleList) {
                    if (roleList.contains(string)) {
                        isOk = 1;
                    }
                }
            }
            // 判断验证结果-只要角色或者用户有一项验证通过则表示验证通过，具有发起该流程的权限
            if (1 != isOk) {
                // 没有权限，返回错误页面
                // 调试阶段先注释起来，后续开放
                return new ModelAndView("errorpage/workerror");
            }
        }
        String taskDefKey = null;
        String formKey = workTaskService.getFormKey(procDefId, taskDefKey);
        ModelAndView model = new ModelAndView("modules/worktask/worktaskform");
        model.addObject("processKey", processKey);
        model.addObject("procDefId", procDefId);
        model.addObject("procDefName", procDefName);
        model.addObject("formKey", formKey);
        return model;
    }

    /**
     * @说明:启动流程
     * @param act procDefKey 流程定义KEY
     * @param act businessTable 业务表表名
     * @param act businessId 业务表编号
     */
    @RequestMapping("start")
    public R start(String processKey, String title, Map<String, Object> vars) throws Exception {
        String userId = UserUtils.getUserId();
        return workTaskService.startProcess(processKey, userId, title, vars);
    }

    /**
     * @说明:待办任务列表页面
     */
    @RequestMapping("todolistpage")
    public ModelAndView todoListPage() {
        return new ModelAndView("modules/worktask/todolist");
    }

    /**
     * @说明:获取待办列表数据
     */
    @RequestMapping("tododata")
    public Map<String, Object> todoList(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return workTaskService.todoList(query, UserUtils.getUserId());
    }

    /**
     * @说明:获取待签收列表数据
     */
    @RequestMapping("tocladata")
    public Map<String, Object> toClaList(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return workTaskService.toClaList(query, UserUtils.getUserId());
    }

    /**
     * @说明:签收任务
     */
    @RequestMapping("claim")
    public R claim(String taskId) {
        String userId = UserUtils.getUserId();
        return workTaskService.claim(taskId, userId);
    }

    /**
     * @说明:完成任务
     */
    @RequestMapping("complete")
    public R complete(String taskId, String processInstanceId, String comment, String title,
            @RequestParam Map<String, Object> vars) {
        String userId = UserUtils.getUserId();
        comment = UserUtils.getUser().getUsername() + "处理任务啦";
        return workTaskService.complete(userId, taskId, processInstanceId, comment, title, vars);
    }

    /**
     * @说明:已办任务列表页面
     */
    @RequestMapping("connectionlistpage")
    public ModelAndView connectionListPage() {
        return new ModelAndView("modules/worktask/already");
    }

    /**
     * @说明:已办任务列表数据
     */
    @RequestMapping("alreadydata")
    public Map<String, Object> alreadyList(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return workTaskService.alreadyList(query, UserUtils.getUserId());
    }

//    /**
//     * @说明:删除任务
//     * @param taskId 流程实例ID
//     * @param reason 删除原因
//     */
//    @RequestMapping(value = "deleteTask")
//    public R deleteTask(String taskId, String reason) {
//        reason = getUser().getUsername() + "删除任务啦";
//        return workTaskService.actTaskService.deleteTask(taskId, reason);
//    }

//    /**
//     * 获取流转历史列表
//     * 
//     * @param act      procInsId 流程实例
//     * @param startAct 开始活动节点名称
//     * @param endAct   结束活动节点名称
//     */
//    @RequestMapping(value = "histoicFlow")
//    public String histoicFlow(Act act, String startAct, String endAct, Model model) {
//        if (StringUtils.isNotBlank(act.getProcInsId())) {
//            List<Act> histoicFlowList = actTaskService.histoicFlowList(act.getProcInsId(), startAct, endAct);
//            model.addAttribute("histoicFlowList", histoicFlowList);
//        }
//        return "modules/act/actTaskHistoricFlow";
//    }
//
//    /**
//     * 获取流程表单
//     * 
//     * @param act taskId 任务ID
//     * @param act taskName 任务名称
//     * @param act taskDefKey 任务环节标识
//     * @param act procInsId 流程实例ID
//     * @param act procDefId 流程定义ID
//     */
//    @RequestMapping(value = "form")
//    public String form(Act act, HttpServletRequest request, Model model) {
//        System.out.println("******---*******----********");
//        // 获取流程XML上的表单KEY
//        String formKey = actTaskService.getFormKey(act.getProcDefId(), act.getTaskDefKey());
//
//        // 获取流程实例对象
//        if (act.getProcInsId() != null) {
//            act.setProcIns(actTaskService.getProcIns(act.getProcInsId()));
//        }
//
//        return "redirect:" + ActUtils.getFormUrl(formKey, act);
//
////		// 传递参数到视图
////		model.addAttribute("act", act);
////		model.addAttribute("formUrl", formUrl);
////		return "modules/act/actTaskForm";
//    }
//
//    /**
//     * 完成任务
//     * 
//     * @param act taskId 任务ID
//     * @param act procInsId 流程实例ID，如果为空，则不保存任务提交意见
//     * @param act comment 任务提交意见的内容
//     * @param act vars 任务流程变量，如下 vars.keys=flag,pass vars.values=1,true
//     *            vars.types=S,B @see
//     *            com.thinkgem.jeesite.modules.act.utils.PropertyType
//     */
//    @RequestMapping(value = "complete")
//    @ResponseBody
//    public String complete(Act act) {
//        actTaskService.complete(act.getTaskId(), act.getProcInsId(), act.getComment(), act.getVars().getVariableMap());
//        return "true";// adminPath + "/act/task";
//    }
//
//    /**
//     * 读取带跟踪的图片
//     */
//    @RequestMapping(value = "trace/photo/{procDefId}/{execId}")
//    public void tracePhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId,
//            HttpServletResponse response) throws Exception {
//        InputStream imageStream = actTaskService.tracePhoto(procDefId, execId);
//
//        // 输出资源内容到相应对象
//        byte[] b = new byte[1024];
//        int len;
//        while ((len = imageStream.read(b, 0, 1024)) != -1) {
//            response.getOutputStream().write(b, 0, len);
//        }
//    }
//
//    /**
//     * 输出跟踪流程信息
//     * 
//     * @param proInsId
//     * @return
//     * @throws Exception
//     */
//    @ResponseBody
//    @RequestMapping(value = "trace/info/{proInsId}")
//    public List<Map<String, Object>> traceInfo(@PathVariable("proInsId") String proInsId) throws Exception {
//        List<Map<String, Object>> activityInfos = actTaskService.traceProcess(proInsId);
//        return activityInfos;
//    }
//
//    /**
//     * 显示流程图
//     * 
//     * @RequestMapping(value = "processPic") public void processPic(String
//     *                       procDefId, HttpServletResponse response) throws
//     *                       Exception { ProcessDefinition procDef =
//     *                       repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
//     *                       String diagramResourceName =
//     *                       procDef.getDiagramResourceName(); InputStream
//     *                       imageStream =
//     *                       repositoryService.getResourceAsStream(procDef.getDeploymentId(),
//     *                       diagramResourceName); byte[] b = new byte[1024]; int
//     *                       len = -1; while ((len = imageStream.read(b, 0, 1024))
//     *                       != -1) { response.getOutputStream().write(b, 0, len); }
//     *                       }
//     */
//
//    /**
//     * 获取跟踪信息
//     * 
//     * @RequestMapping(value = "processMap") public String processMap(String
//     *                       procDefId, String proInstId, Model model) throws
//     *                       Exception { List<ActivityImpl> actImpls = new
//     *                       ArrayList<ActivityImpl>(); ProcessDefinition
//     *                       processDefinition = repositoryService
//     *                       .createProcessDefinitionQuery().processDefinitionId(procDefId)
//     *                       .singleResult(); ProcessDefinitionImpl pdImpl =
//     *                       (ProcessDefinitionImpl) processDefinition; String
//     *                       processDefinitionId = pdImpl.getId();// 流程标识
//     *                       ProcessDefinitionEntity def = (ProcessDefinitionEntity)
//     *                       ((RepositoryServiceImpl) repositoryService)
//     *                       .getDeployedProcessDefinition(processDefinitionId);
//     *                       List<ActivityImpl> activitiList =
//     *                       def.getActivities();// 获得当前任务的所有节点 List<String>
//     *                       activeActivityIds =
//     *                       runtimeService.getActiveActivityIds(proInstId); for
//     *                       (String activeId : activeActivityIds) { for
//     *                       (ActivityImpl activityImpl : activitiList) { String id
//     *                       = activityImpl.getId(); if (activityImpl.isScope()) {
//     *                       if (activityImpl.getActivities().size() > 1) {
//     *                       List<ActivityImpl> subAcList = activityImpl
//     *                       .getActivities(); for (ActivityImpl subActImpl :
//     *                       subAcList) { String subid = subActImpl.getId();
//     *                       System.out.println("subImpl:" + subid); if
//     *                       (activeId.equals(subid)) {// 获得执行到那个节点
//     *                       actImpls.add(subActImpl); break; } } } } if
//     *                       (activeId.equals(id)) {// 获得执行到那个节点
//     *                       actImpls.add(activityImpl); System.out.println(id); } }
//     *                       } model.addAttribute("procDefId", procDefId);
//     *                       model.addAttribute("proInstId", proInstId);
//     *                       model.addAttribute("actImpls", actImpls); return
//     *                       "modules/act/actTaskMap"; }
//     */
//

}