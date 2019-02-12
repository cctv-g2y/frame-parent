package com.makun.dao.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.makun.entity.system.SysUser;

/**
 * @说明：[系统用户表数据层控制接口]
 * @author: makun
 */

public interface SysUserDao extends BaseMapper<SysUser> {
    /**
     * @说明:无条件查询用户列表
     */
    public List<SysUser> findAlluser();

    /**
     * @说明:通过用户名查询用户信息
     */
    public SysUser getUserByName(String username);

    /**
     * @说明:查询用户的所有菜单ID
     */
    public List<String> queryAllMenuId(String userId);

    public List<SysUser> queryPageList(Page<SysUser> page, Map<String, Object> map);

    public List<SysUser> queryList(Map<String, Object> map);

    /**
     * @说明: 查询用户的所有权限
     */
    public List<String> queryAllPerms(String userId);

    /**
     * @说明:根据用户名，查询系统用户
     */
    public SysUser queryByUserName(String username);

    public int deleteSysUserBatch(Object[] id);

    public int deleteSysUserRoleBatch(Object[] id);

    public List<SysUser> deptUserList(Map<String, Object> map);

    public SysUser selectByName(String name);

    public SysUser findById(String id);

    public List<SysUser> selectByPid(List<String> list);

    /**
     * @说明：[根据人员id集合查询收件人集合]
     */
    public List<SysUser> selectUserList(String[] ids);

    public List<Map<String, Object>> queryUserByDeptId(String deptId);

    public String getAvatar(String id);

    public List<Map<String, Object>> getUserListByRoles(String deptId);

    public List<SysUser> selectAll();

    public List<SysUser> pageByDeptId(Map<String, Object> map);

    public List<String> queryRoleByUserId(String userId);

}
