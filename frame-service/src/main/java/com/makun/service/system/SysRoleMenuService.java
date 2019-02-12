package com.makun.service.system;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.system.SysRoleMenu;

/**
 * @说明：[角色菜单对应service层]
 * @author：makun
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 根据角色id查看该角色对应的权限
     */
    public List<SysRoleMenu> showMenuByRoleId(String roleId);

    public void saveOrUpdate(String roleId, List<String> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    public List<String> queryMenuIdList(String roleId);

}
