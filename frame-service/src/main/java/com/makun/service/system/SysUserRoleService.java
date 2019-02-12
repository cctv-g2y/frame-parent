package com.makun.service.system;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.system.SysRole;
import com.makun.entity.system.SysUserRole;

/**
 * @说明：[用户对应角色service] @author：makun
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户id查询对应的角色
     */
    public List<SysUserRole> findGroupsByUser(String userId);

    /**
     * 根据用户ID，获取角色ID列表
     */
    public List<String> queryRoleIdList(String userId);

    /**
     * 根据用户ID，获取角色列表
     */
    public List<SysRole> queryRoleList(String userId);

    public void saveOrUpdate(String userId, List<String> roleIdList);

    public void deleteByUserId(String userId);

    public List<String> queryRoleIdAll();

    /**
     * 根据角色id查询对应的用户集合
     */
    public List<String> getUserByRoleId(String roleId);

}
