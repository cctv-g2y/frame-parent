package com.makun.service.workflow;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.workflow.Creatorconfine;

/**
 * @说明：流程发起权限控制service
 * @author makun
 */
public interface CreatorconfineService extends IService<Creatorconfine> {

    /**
     * @说明：根据模型id查询控制权限
     */
    public Creatorconfine getByModelId(String modelId);

    /**
     * @说明：根据流程定义key查询控制权限
     */
    public Creatorconfine getByDefKey(String key);

    /**
     * @说明：根据模型id删除控制权限
     */
    public void delByModelId(String modelId);

}
