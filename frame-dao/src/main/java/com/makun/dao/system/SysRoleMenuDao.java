package com.makun.dao.system;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.makun.entity.system.SysRoleMenu;

/**
 * @说明：[角色对应菜单Dao]
 * @author：makun
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {

    /**
     * 根据角色id查看该角色对应的权限
     */
    public List<SysRoleMenu> showMenuByRoleId(String roleId);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    public List<String> queryMenuIdList(String roleId);

    public void deleteByRoleId(String roleId);

    public void deleteByMenuId(String menuId);

}
