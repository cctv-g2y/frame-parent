package com.makun.service.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.system.SysMenu;

/**
 * @说明：[系统菜单service]
 * @author：makun
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 查询菜单列表
     */
    public List<SysMenu> queryList(Map<String, Object> map);

    /**
     * 根据父菜单，查询子菜单
     * 
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * 
     * @param parentId 父菜单ID
     */
    public List<SysMenu> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    public List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    public List<SysMenu> getUserMenuList(String userId);

    /**
     * 删除
     */
    public void delById(String menuIds);

    /**
     * 查询用户的权限列表
     */
    public List<SysMenu> queryUserList(String userId);

    /**
     * 根据pId查询菜单列表
     */
    public List<SysMenu> getListByPid(String pId);

}
