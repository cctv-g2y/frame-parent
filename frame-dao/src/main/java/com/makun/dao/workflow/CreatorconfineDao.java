package com.makun.dao.workflow;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.makun.entity.workflow.Creatorconfine;

/**
 * @说明：流程发起权限控制dao
 * @author makun
 */
public interface CreatorconfineDao extends BaseMapper<Creatorconfine> {

    /**
     * @说明：根据模型id查询控制权限
     */
    public List<Creatorconfine> getByModelId(String modelId);

    /**
     * @说明：根据流程定义key查询控制权限
     */
    public Creatorconfine getByDefKey(String key);

    /**
     * @说明：根据模型id删除控制权限
     */
    public void delByModelId(String modelId);

}
