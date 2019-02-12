package com.makun.dao.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.makun.entity.system.SysMenu;

/**
 * @说明：[系统菜单dao]
 * @author：makun
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {

    /**
     * 获取所有菜单列表
     * 
     * @param map
     * @return List<SysMenu>
     */
    public List<SysMenu> queryList(Map<String, Object> map);

    /**
     * 根据父级菜单id查询子菜单
     */
    public List<SysMenu> queryListParentId(String parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    public List<SysMenu> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    public List<SysMenu> queryUserList(String userId);

    /**
     * 根据父级id获取所有父级菜单
     */
    public List<SysMenu> getListByPid(String pId);

    /**
     * 删除菜单
     */
    public int delById(String id);

}
