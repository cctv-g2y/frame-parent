package com.makun.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.system.SysDeptDao;
import com.makun.entity.system.SysDept;
import com.makun.service.system.SysDeptService;

/**
 * @说明：[系统部门service实现层]
 * @author：makun
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDept> implements SysDeptService {

    @Autowired
    private SysDeptDao sysDeptDao;

    /**
     * 展示部门
     */
    @Override
    public List<SysDept> queryList(Map<String, Object> map) {
        return sysDeptDao.queryList(map);
    }

    /**
     * 查询该部门的子部门
     */
    @Override
    public List<String> queryDetpIdList(String parentId) {
        return sysDeptDao.queryDetpIdList(parentId);
    }

    /**
     * 递归
     */
    @Override
    public void getDeptTreeList(List<String> subIdList, List<String> deptIdList) {
        for (String deptId : subIdList) {
            List<String> list = queryDetpIdList(deptId);
            if (list.size() > 0) {
                getDeptTreeList(list, deptIdList);
            }
            deptIdList.add(deptId);
        }
    }

}
