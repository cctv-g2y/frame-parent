package com.makun.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.system.SysRoleMenuDao;
import com.makun.entity.system.SysRoleMenu;
import com.makun.service.system.SysRoleMenuService;

/**
 * @说明：[角色对应菜单service实现层]
 * @author：makun
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 根据角色id查看该角色对应的权限
     */
    @Override
    public List<SysRoleMenu> showMenuByRoleId(String roleId) {
        return sysRoleMenuDao.showMenuByRoleId(roleId);
    }

    @Override
    @Transactional
    public void saveOrUpdate(String roleId, List<String> menuIdList) {
        // 先删除角色与菜单关系
        sysRoleMenuDao.deleteByRoleId(roleId);
        if (menuIdList.size() == 0) {
            return;
        } else {
            // 保存角色与菜单关系
            for (String string : menuIdList) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(string);
                sysRoleMenuDao.insert(sysRoleMenu);
            }
        }
    }

    @Override
    public List<String> queryMenuIdList(String roleId) {
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }

}
