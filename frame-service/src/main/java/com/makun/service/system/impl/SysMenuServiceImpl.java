package com.makun.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.system.SysMenuDao;
import com.makun.entity.system.SysMenu;
import com.makun.service.system.SysMenuService;
import com.makun.service.system.SysUserService;
import com.makun.dao.system.SysRoleMenuDao;

/**
 * @说明：[系统菜单service实现层]
 * @author：makun
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 获取所有菜单列表
     */
    @Override
    public List<SysMenu> queryList(Map<String, Object> map) {
        return sysMenuDao.queryList(map);
    }

    /**
     * 根据父菜单，查询子菜单
     * 
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    @Override
    public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 根据父菜单，查询子菜单
     * 
     * @param parentId 父菜单ID
     */
    @Override
    public List<SysMenu> queryListParentId(String parentId) {
        return sysMenuDao.queryListParentId(parentId);
    }

    /**
     * 获取用户菜单列表
     */
    @Override
    public List<SysMenu> getUserMenuList(String userId) {
        // admin，拥有最高权限
        if (userId.equals("1")) {
            return getAllMenuList(null);
        }
        // 用户菜单列表
        List<String> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 删除
     */
    @Override
    @Transactional
    public void delById(String menuIds) {
        sysMenuDao.delById(menuIds);
        sysRoleMenuDao.deleteByMenuId(menuIds);
    }

    /**
     * 查询用户的权限列表
     */
    @Override
    public List<SysMenu> queryUserList(String userId) {
        return sysMenuDao.queryUserList(userId);
    }

    /**
     * 获取不包含按钮的菜单
     */
    @Override
    public List<SysMenu> queryNotButtonList() {
        return sysMenuDao.queryNotButtonList();
    }

    @Override
    public List<SysMenu> getListByPid(String pId) {
        return sysMenuDao.getListByPid(pId);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<String> menuIdList) {
        // 查询根菜单列表
        List<SysMenu> menuList = queryListParentId("0", menuIdList);
        // 递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<String> menuIdList) {
        List<SysMenu> subMenuList = new ArrayList<SysMenu>();

        for (SysMenu entity : menuList) {
            entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            subMenuList.add(entity);
        }
        return subMenuList;
    }

}
