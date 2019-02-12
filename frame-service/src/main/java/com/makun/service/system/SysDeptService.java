package com.makun.service.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.system.SysDept;

/**
 * @说明：[系统部门service]
 * @author：makun
 */
public interface SysDeptService extends IService<SysDept> {

    /**
     * 展示部门
     */
    public List<SysDept> queryList(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     */
    public List<String> queryDetpIdList(String parentId);

    public void getDeptTreeList(List<String> subIdList, List<String> deptIdList);

}
