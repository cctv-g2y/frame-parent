package com.makun.dao.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.makun.entity.system.SysDept;

/**
 * 
 * @说明：[系统部门dao]
 * @author：makun
 */
public interface SysDeptDao extends BaseMapper<SysDept> {

    /**
     * 展示部门列表
     */
    public List<SysDept> queryList(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     */
    public List<String> queryDetpIdList(String parentId);

}
