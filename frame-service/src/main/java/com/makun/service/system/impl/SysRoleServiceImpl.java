package com.makun.service.system.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.system.SysRoleDao;
import com.makun.entity.system.SysRole;
import com.makun.service.system.SysRoleMenuService;
import com.makun.service.system.SysRoleService;

/**
 * @说明：[系统角色service实现层]
 * @author：makun
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public Page<SysRole> queryPageList(Page<SysRole> page, Map<String, Object> map) {
        page.setRecords(sysRoleDao.queryPageList(page, map));
        return page;
    }

    @Override
    public List<SysRole> queryList(Map<String, Object> map) {
        return sysRoleDao.queryList(map);
    }

    @Override
    @Transactional
    public void save(SysRole role) {
        sysRoleDao.insert(role);
        // 保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void update(SysRole role) {
        sysRoleDao.updateById(role);
        // 更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional
    public void deleteBatch(String[] roleIds) {
        sysRoleDao.deleteSysRoleByRoleId(roleIds);
        sysRoleDao.deleteSysRoleMenuByRoleId(roleIds);
        sysRoleDao.deleteSysUserRoleByRoleId(roleIds);
    }

}
