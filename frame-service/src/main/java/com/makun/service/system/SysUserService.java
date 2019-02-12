package com.makun.service.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.system.SysUser;

/**
 * @说明：[系统用户service层]
 * @author: makun
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 无条件查询用户列表
     */
    public List<SysUser> findAlluser();

    /**
     * 通过用户名获取用户信息
     */
    public SysUser getUser(String username);

    /**
     * 查询用户的所有菜单ID
     */
    public List<String> queryAllMenuId(String userId);

    public Page<SysUser> queryPageList(Page<SysUser> pageUtil, Map<String, Object> map);

    /**
     * 查询用户列表
     */
    public List<SysUser> queryList(Map<String, Object> map);

    /**
     * 查询用户的所有权限
     */
    public List<String> queryAllPerms(String userId);

    /**
     * 根据用户名，查询系统用户
     */
    public SysUser queryByUserName(String username);

    /**
     * 删除用户
     */
    public void deleteBatch(String[] userIds);

    public void save(SysUser user);

    public void update(SysUser user);

    public List<SysUser> deptUserList(Map<String, Object> map);

    public SysUser selectByName(String name);

    public SysUser findById(String id);

    public List<SysUser> selectByPid(List<String> list);

    /**
     * 说明：[根据人员id集合查询收件人集合]
     */
    public List<SysUser> selectUserList(String[] ids);

    /**
     * 根据部门Id查询用户，im好友列表用
     */
    public List<Map<String, Object>> queryUserByDeptId(String deptId);

    public String getAvatar(String id);

    public List<Map<String, Object>> getUserListByRoles(String deptId);

    public List<SysUser> selectAll();

    public Page<SysUser> pageByDeptId(Page<SysUser> pageUtil, Map<String, Object> map);

    public String getUserNames(String userids);

    public List<String> queryRoleByUserId(String userId);

}
