package com.makun.service.worktask;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.worktask.WorkTask;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;

/**
 * @说明：工作流程主体服务层
 * @author makun
 */
public interface WorkTaskService extends IService<WorkTask> {

    /**
     * @说明:获取待办任务列表
     */
    public Map<String, Object> todoList(QueryUtil query, String userId);

    /**
     * @说明:获取待签收任务列表
     */
    public Map<String, Object> toClaList(QueryUtil query, String userId);

    /**
     * @说明:获取已发任务列表
     */
    public Map<String, Object> sentList(QueryUtil query, String userId);

    /**
     * @说明:待发界面获取流程列表
     */
    public Map<String, Object> processList(QueryUtil query);

    /**
     * @说明:启动流程
     */
    public R startProcess(String processKey, String userId, String title, Map<String, Object> vars);

    /**
     * @说明:签收任务
     */
    public R claim(String taskId, String userId);

    /**
     * @说明:完成任务
     */
    public R complete(String userId, String taskId, String processInstanceId, String comment, String title,
            Map<String, Object> vars);

    /**
     * @说明:已办任务列表数据
     */
    public Map<String, Object> alreadyList(QueryUtil query, String userId);

    /**
     * @说明：获取流程表单（首先获取任务节点表单KEY，如果没有则取流程开始节点表单KEY）
     */
    public String getFormKey(String procDefId, String taskDefKey);

}
