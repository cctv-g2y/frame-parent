package com.makun.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.system.SysUserRoleDao;
import com.makun.entity.system.SysRole;
import com.makun.entity.system.SysUserRole;
import com.makun.service.system.SysUserRoleService;

/**
 * @说明：[系统用户对应角色service实现层] @author：makun
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 根据用户id查询角色集合
     */
    @Override
    public List<SysUserRole> findGroupsByUser(String userId) {
        return sysUserRoleDao.findGroupsByUser(userId);
    }

    @Override
    public void saveOrUpdate(String userId, List<String> roleIdList) {
        if (roleIdList.size() == 0) {
            return;
        }

        // 先删除用户与角色关系
        sysUserRoleDao.deleteByUserId(userId);
        // 保存用户与角色关系
        for (String string : roleIdList) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(string);
            sysUserRoleDao.insert(sysUserRole);
        }
    }

    @Override
    public List<String> queryRoleIdList(String userId) {
        return sysUserRoleDao.queryRoleIdList(userId);
    }

    @Override
    public void deleteByUserId(String userId) {
        sysUserRoleDao.deleteByUserId(userId);
    }

    @Override
    public List<String> queryRoleIdAll() {
        return sysUserRoleDao.queryRoleIdAll();
    }

    @Override
    public List<SysRole> queryRoleList(String userId) {
        return sysUserRoleDao.queryRoleList(userId);
    }

    /**
     * 根据角色id查询对应的用户集合
     */
    @Override
    public List<String> getUserByRoleId(String roleId) {
        return sysUserRoleDao.getUserByRoleId(roleId);
    }

}
