package com.makun.worklistener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.EngineServices;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricProcessInstance;

import com.makun.config.base.UserUtils;
import com.makun.entity.system.SysUser;
import com.makun.service.system.SysUserRoleService;
import com.makun.service.system.SysUserService;
import com.makun.utils.SpringUtil;

/**
 * @说明:全局监听器-包括start--启动监听,end--结束监听,take--连线监听;并可以控制该流程发起人
 * @author makun
 */
@SuppressWarnings({ "unused", "deprecation" })
public class MyExecutionListener implements ExecutionListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateExecution execution) throws Exception {

        // 获取用户service
        SysUserService sysuserService = SpringUtil.getBean(SysUserService.class);
        // 获取角色对应用户service
        SysUserRoleService sysUserRoleService = SpringUtil.getBean(SysUserRoleService.class);

        /** 这个比较有用 主要就是start、end、take,所以可以将这三类监听同时完成 */
        String eventName = execution.getEventName();

        /** 这个非常有用。当拿到EngineServices 对象所有的xxxService都可以拿到。 */
        EngineServices engineServices = execution.getEngineServices();

        IdentityService identityService = engineServices.getIdentityService();

        HistoryService historyService = engineServices.getHistoryService();

        /** 流程实例id */
        String processInstanceId = execution.getProcessInstanceId();

        if ("start".equals(eventName)) {
            // c9e3202c3f9a4b5da6d4c8aede8ac807-周启
            execution.setVariable("inputUser", "c9e3202c3f9a4b5da6d4c8aede8ac807");
            // 4e9d699a85644ac98e2533a910e4594a-测试用户的角色
            List<String> userByRoleId = sysUserRoleService.getUserByRoleId("4e9d699a85644ac98e2533a910e4594a");
            Collection<String> candidateUsers = new ArrayList<String>();
            // 该角色有与之对应的用户
            if (null != userByRoleId && !userByRoleId.isEmpty()) {
                candidateUsers.addAll(userByRoleId);
            } else {
                // 该角色没有对应的角色-则分配给周启
                candidateUsers.add("c9e3202c3f9a4b5da6d4c8aede8ac807");
            }
            execution.setVariable("roles", candidateUsers);
            SysUser createUser = UserUtils.getUser();
            System.out.println(createUser.getUsername() + "发起的流程,当前对象已经获取,个人信息都有,通知发起人");
            /** act认证发起人,引擎会记录启动人，即在ACT_HI_PROINST表的START_USER_ID字段 */
            /** 启动代码中如果已经处理,这里就不需要再做处理了 */
//            identityService.setAuthenticatedUserId(createUser.getUserId());
            System.out.println("start=========");
        } else if ("end".equals(eventName)) {
            /** 获取流程发起人,这里获取到历史流程实例,再获取到发起人id,通过systemService获取用户信息,通知发起人,流程结束 */
            HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();
            String startUserId = processInstance.getStartUserId();
            SysUser user = sysuserService.selectById(startUserId);
            System.out.println(user.getUsername() + "发起的流程,当前对象已经获取,个人信息都有,通知发起人");
            System.out.println("end=========");
        } else if ("take".equals(eventName)) {
            System.out.println("take=========");
        }

        /** execution Id */
        String id = execution.getId();

        /** 业务id已经废弃 */
        String businessKey = execution.getBusinessKey();

        /** 业务id */
        String processBusinessKey = execution.getProcessBusinessKey();

        /** 流程定义id */
        String processDefintionId = execution.getProcessDefinitionId();

        /** 获取父id，并发的时候有用 */
        String parentId = execution.getParentId();

        /** 获取当前的Activityid,就是当前激活的节点id */
        String activityId = execution.getCurrentActivityId();

        /** 获取当前的.Activity name,当前激活节点的name */
        String activityName = execution.getCurrentActivityName();

        /** 获取TenantId 当有多个TenantId 有用 */
        String tenantId = execution.getTenantId();

    }

}