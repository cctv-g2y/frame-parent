package com.makun.worklistener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.makun.entity.system.SysUserRole;
import com.makun.service.system.SysUserRoleService;
import com.makun.utils.SpringUtil;

/**
 * @说明:分配给makun监听器
 * @author makun
 */
@SuppressWarnings("unused")
public class MyTaskListener implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        // 获取角色对应用户service
        SysUserRoleService sysUserRoleService = SpringUtil.getBean(SysUserRoleService.class);
        String eventName = delegateTask.getEventName();
        if ("create".endsWith(eventName)) {
            delegateTask.setVariable("inputUser", "2");
            Map<String, Object> variables = delegateTask.getVariables();
            System.out.println(variables + "create");
            System.out.println("create=========");
        } else if ("assignment".endsWith(eventName)) {
            System.out.println("assignment========");
        } else if ("complete".endsWith(eventName)) {
            System.out.println("complete===========");
        } else if ("delete".endsWith(eventName)) {
            System.out.println("delete=============");
        }
    }
//    String descriptions = "任务描述";
//    String taskNames = "任务名称";
//
//    /**
//     * 任务优先级 lower priority: [0..19] lowest, [20..39] low, [40..59] normal, [60..79]
//     * high [80..100] highest 任务处理的优先级范围是0-100
//     */
//    int prioritys = 10;
//    String assignee = "拥有人";
//    String userIds = "候选人";
//    Collection<String> candidateUsers = new ArrayList<String>();
//    candidateUsers.add("候选人1");
//    candidateUsers.add("候选人2");
//    String groupIds = "候选组";
//    /** 动作名称:create,assignment,complete,delete */
//    String eventName = delegateTask.getEventName();
//    /**
//     * @说明:如果配置了处理人,则执行顺序为:assignment==>create==>complete==>delete
//     */
//    if ("create".endsWith(eventName)) {
//        System.out.println("create=========");
//    } else if ("assignment".endsWith(eventName)) {
//        System.out.println("assignment========");
//    } else if ("complete".endsWith(eventName)) {
//        System.out.println("complete===========");
//    } else if ("delete".endsWith(eventName)) {
//        System.out.println("delete=============");
//    }
//    /** 数据库中的taskId主键 */
//    String taskId = delegateTask.getId();
//    /** 任务名称 */
//    String taskName = delegateTask.getName();
//    /** 修改任务名称 */
//    delegateTask.setName(taskNames);
//    /** 修改任务的所属人 */
//    delegateTask.setAssignee(assignee);
//    /** 获取任务的描述信息 */
//    String description = delegateTask.getDescription();
//    /** 修改任务的描述信息 */
//    delegateTask.setDescription(descriptions);
//    /**
//     * @说明:获取任务的优先级 lower priority: [0..19] lowest, [20..39] low, [40..59] normal,
//     *              [60..79] high [80..100] highest 任务处理的优先级范围是0-100
//     */
//    delegateTask.getPriority();
//    /** 修改优先级 */
//    delegateTask.setPriority(prioritys);
//    /** 获取流程实例id */
//    String processInstanceId = delegateTask.getProcessInstanceId();
//    /** 获取执行id */
//    String executionId = delegateTask.getExecutionId();
//    /** 获取流程定义id */
//    String processDefinitionId = delegateTask.getProcessDefinitionId();
//    /** Adds the given user as a candidate user to this task.给任务添加候选人 */
//    delegateTask.addCandidateUser(userIds);
//    /** 一次添加多个候选人 */
//    delegateTask.addCandidateUsers(candidateUsers);
//    /** 添加候选组 */
//    delegateTask.addCandidateGroup(groupIds);

}
