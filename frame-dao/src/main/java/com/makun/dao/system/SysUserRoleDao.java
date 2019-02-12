package com.makun.dao.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.makun.entity.system.SysRole;
import com.makun.entity.system.SysUserRole;

/**
 * @说明：[用户对应角色dao] @author：makun
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRole> {

    /**
     * 根据用户id查询角色集合
     * 
     * @param userId
     * @return List<SysUserRole>
     */
    public List<SysUserRole> findGroupsByUser(String userId);

    public List<SysUserRole> queryList(Map<String, Object> map);

    /**
     * 根据用户ID，获取角色ID列表
     */
    public List<String> queryRoleIdList(String userId);

    public int deleteByUserId(String userId);

    public List<String> queryRoleIdAll();

    public List<SysRole> queryRoleList(String userId);

    /**
     * 根据角色id查询对应的用户集合
     */
    public List<String> getUserByRoleId(String roleId);

}
