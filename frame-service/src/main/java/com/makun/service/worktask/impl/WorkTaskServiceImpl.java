package com.makun.service.worktask.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.makun.dao.worktask.WorkTaskDao;
import com.makun.entity.workflow.Creatorconfine;
import com.makun.entity.worktask.WorkTask;
import com.makun.service.system.SysUserRoleService;
import com.makun.service.system.SysUserService;
import com.makun.service.workflow.CreatorconfineService;
import com.makun.service.worktask.WorkTaskService;
import com.makun.utils.DateUtils;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;
import com.makun.utils.SpringUtil;
import com.makun.utils.StringUtils;

/**
 * @说明：工作流主体服务层
 * @author makun
 */
@Service
public class WorkTaskServiceImpl extends ServiceImpl<WorkTaskDao, WorkTask> implements WorkTaskService {

    @Autowired
    private SysUserService sysuserService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private CreatorconfineService creatorconfineService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * @说明:获取待办任务列表
     */
    @Override
    public Map<String, Object> todoList(QueryUtil query, String userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        String search = (String) query.get("search");
        TaskQuery todoTaskQuery = taskService.createTaskQuery().taskAssignee(userId).active().includeProcessVariables()
                .orderByTaskCreateTime().desc();
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            // 按照任务名称模糊查询
            search = "%" + search + "%";
            todoTaskQuery = todoTaskQuery.taskNameLike(search);
        }
        resultMap.put("total", todoTaskQuery.count());
        List<Task> todoList = todoTaskQuery.listPage(query.getOffset(), query.getLimit());
        for (Task task : todoList) {
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processDefinitionId).singleResult();
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("taskId", task.getId());
            maps.put("taskName", task.getName());
            maps.put("processDefinitionName", processDefinition.getName());
            maps.put("version", processDefinition.getVersion());
            maps.put("createTime", DateUtils.format(task.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("processDefinitionId", task.getProcessDefinitionId());
            maps.put("processInstanceId", task.getProcessInstanceId());
            maps.put("description", task.getDescription());
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明:获取待签收任务列表
     */
    @Override
    public Map<String, Object> toClaList(QueryUtil query, String userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        String search = (String) query.get("search");
        TaskQuery toclaTaskQuery = taskService.createTaskQuery().taskCandidateUser(userId).active()
                .includeProcessVariables().orderByTaskCreateTime().desc();
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            // 按照任务名称模糊查询
            search = "%" + search + "%";
            toclaTaskQuery = toclaTaskQuery.taskNameLike(search);
        }
        resultMap.put("total", toclaTaskQuery.count());
        List<Task> todoList = toclaTaskQuery.listPage(query.getOffset(), query.getLimit());
        for (Task task : todoList) {
            String userIds = "";
            String processDefinitionId = task.getProcessDefinitionId();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processDefinitionId).singleResult();
            List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(task.getId());
            for (IdentityLink task3 : identityLinksForTask) {
                userIds += task3.getUserId() + ",";
            }
            String userNames = sysuserService.getUserNames(userIds);
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("userNames", userNames);
            maps.put("taskId", task.getId());
            maps.put("taskName", task.getName());
            maps.put("processDefinitionName", processDefinition.getName());
            maps.put("version", processDefinition.getVersion());
            maps.put("createTime", DateUtils.format(task.getCreateTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("processDefinitionId", task.getProcessDefinitionId());
            maps.put("processInstanceId", task.getProcessInstanceId());
            maps.put("description", task.getDescription());
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明:获取已发任务列表
     */
    @Override
    public Map<String, Object> sentList(QueryUtil query, String userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        String search = (String) query.get("search");
        HistoricProcessInstanceQuery processQuery = historyService.createHistoricProcessInstanceQuery()
                .startedBy(userId).orderByProcessInstanceStartTime().desc();
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            // 按照流程名称模糊查询
            search = "%" + search + "%";
//            processQuery = processQuery.processInstanceNameLike(search);
            processQuery = processQuery.processDefinitionName(search);
        }
        resultMap.put("total", processQuery.count());
        List<HistoricProcessInstance> listPage = processQuery.listPage(query.getOffset(), query.getLimit());
        for (HistoricProcessInstance historicProcessInstance : listPage) {
            Map<String, Object> maps = new HashMap<String, Object>();
            ProcessInstance singleResult = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(historicProcessInstance.getId()).singleResult();
            maps.put("processDefinitionId", historicProcessInstance.getProcessDefinitionId());
            maps.put("processInstanceId", historicProcessInstance.getId());
            maps.put("processDefinitionName", historicProcessInstance.getProcessDefinitionName());
            maps.put("processDefinitionKey", historicProcessInstance.getProcessDefinitionKey());
            maps.put("processDefinitionVersion", historicProcessInstance.getProcessDefinitionVersion());
            maps.put("startTime",
                    DateUtils.format(historicProcessInstance.getStartTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("endTime", DateUtils.format(historicProcessInstance.getEndTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("description", historicProcessInstance.getDescription());
            maps.put("status", null == singleResult ? "1" : "0");
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明:待发界面获取流程列表
     */
    @Override
    public Map<String, Object> processList(QueryUtil query) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        String search = (String) query.get("search");
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().latestVersion()
                .active().orderByProcessDefinitionKey().asc();
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            // 按照流程名称模糊查询
            search = "%" + search + "%";
            processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike(search);
        }
        resultMap.put("total", processDefinitionQuery.count());
        List<ProcessDefinition> listPage = processDefinitionQuery.listPage(query.getOffset(), query.getLimit());
        for (ProcessDefinition processDefinition : listPage) {
            Map<String, Object> maps = new HashMap<String, Object>();
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            maps.put("category", processDefinition.getCategory());
            maps.put("key", processDefinition.getKey());
            maps.put("name", processDefinition.getName());
            maps.put("version", processDefinition.getVersion());
            maps.put("deploymentTime", DateUtils.format(deployment.getDeploymentTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("id", processDefinition.getId());
            maps.put("diagramResourceName", processDefinition.getDiagramResourceName());
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明:启动流程
     * @param processKey 流程定义KEY
     * @param title      流程标题，显示在待办任务标题
     * @param vars       流程变量
     * @return 流程实例ID
     */
    @Override
    @Transactional(readOnly = false)
    public R startProcess(String processKey, String userId, String title, Map<String, Object> vars) {
        Creatorconfine creatorconfine = creatorconfineService.getByDefKey(processKey);
        if (null != creatorconfine) {
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
                return R.error("发起失败，没有权限");
            }
        }
        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(userId);

        // 设置流程变量
        if (vars == null) {
            vars = Maps.newHashMap();
        }

        // 设置流程标题
        if (StringUtils.isNotBlank(title)) {
            vars.put("title", title);
        }
        vars.put("days", 2);
        // 启动流程
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey, processKey, vars);
        // 这里启动流程的时候可以第二个参数businessKey可以放入业务id，从而与业务相关联，一般采用业务名+id：e.g：'leave:1'
//        runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        // 在通过流程实例获取到BUSINESS_KEY，再从BUSINESS_KEY获取到业务id，再通过业务id查询出业务
        // 由于我们是通过业务名：id的形式存储，所以截取出来取后面的值
//        String businessKey = pi.getBusinessKey();
//        String yewuId = businessKey.split(":")[1];
//        Yewu yewu = yewuService.selectById(yewuId);
        return R.ok("发起成功").put("procInstId", pi.getId());
    }

    /**
     * @说明:签收任务
     */
    @Override
    public R claim(String taskId, String userId) {
        try {
            taskService.claim(taskId, userId);
            return R.ok("签收成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("签收失败");
        }
    }

    /**
     * @说明:完成任务
     */
    @Override
    @Transactional
    public R complete(String userId, String taskId, String processInstanceId, String comment, String title,
            Map<String, Object> vars) {
        // 添加意见
        if (StringUtils.isNotBlank(processInstanceId) && StringUtils.isNotBlank(comment)) {
            taskService.addComment(taskId, processInstanceId, comment);
        }
        // 设置流程变量
        if (vars == null) {
            // 流程变量是以map形式添加
            vars = Maps.newHashMap();
        }
        // 设置流程标题
        if (StringUtils.isNotBlank(title)) {
            vars.put("title", title);
        }
        // 提交任务
        taskService.complete(taskId, vars);
        return R.ok("处理成功");
    }

    /**
     * @说明:已办任务列表数据
     */
    @Override
    public Map<String, Object> alreadyList(QueryUtil query, String userId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        HistoricTaskInstanceQuery histTaskQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId)
                .finished().includeProcessVariables().orderByHistoricTaskInstanceEndTime().desc();
        String search = (String) query.get("search");
        if (null != search && !"".equals(search) && !"null".equals(search)) {
            // 按照任务名称模糊查询
            search = "%" + search + "%";
            histTaskQuery = histTaskQuery.taskNameLike(search);
        }
        resultMap.put("total", histTaskQuery.count());
        List<HistoricTaskInstance> listPage = histTaskQuery.listPage(query.getOffset(), query.getLimit());
        for (HistoricTaskInstance historicTaskInstance : listPage) {
            String processDefinitionId = historicTaskInstance.getProcessDefinitionId();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processDefinitionId).singleResult();
            ProcessInstance singleResult = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(historicTaskInstance.getProcessInstanceId()).singleResult();
            Map<String, Object> maps = new HashMap<String, Object>();
            maps.put("processDefinitionName", processDefinition.getName());
            maps.put("version", processDefinition.getVersion());
            maps.put("endTime", DateUtils.format(historicTaskInstance.getEndTime(), DateUtils.DATE_TIME_PATTERN));
            maps.put("processDefinitionId", historicTaskInstance.getProcessDefinitionId());
            maps.put("processInstanceId", historicTaskInstance.getProcessInstanceId());
            maps.put("taskDefinitionKey", historicTaskInstance.getTaskDefinitionKey());
            maps.put("taskName", historicTaskInstance.getName());
            maps.put("status", null == singleResult ? "1" : "0");
            lists.add(maps);
        }
        resultMap.put("rows", lists);
        return resultMap;
    }

    /**
     * @说明：获取流程表单（首先获取任务节点表单KEY，如果没有则取流程开始节点表单KEY）
     */
    @Override
    public String getFormKey(String procDefId, String taskDefKey) {
        String formKey = "";
        if (StringUtils.isNotBlank(procDefId)) {
            if (StringUtils.isNotBlank(taskDefKey)) {
                try {
                    formKey = formService.getTaskFormKey(procDefId, taskDefKey);
                } catch (Exception e) {
                    formKey = "";
                }
            }
            if (StringUtils.isBlank(formKey)) {
                formKey = formService.getStartFormKey(procDefId);
            }
            if (StringUtils.isBlank(formKey)) {
                formKey = SpringUtil.getApplicationContext().getApplicationName() + "/errorpage/404.html";
            }
        }
        return formKey;
    }

}
