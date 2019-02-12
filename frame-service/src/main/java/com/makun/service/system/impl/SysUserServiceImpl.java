package com.makun.service.system.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.system.SysUserDao;
import com.makun.entity.system.SysUser;
import com.makun.service.system.SysUserRoleService;
import com.makun.service.system.SysUserService;

/**
 * @说明：[系统用户service实现层]
 * @author: makun
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserDao sysuserDao;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 无条件查询用户列表
     */
    @Override
    public List<SysUser> findAlluser() {
        return sysuserDao.findAlluser();
    }

    /**
     * 通过用户名获取用户信息
     */
    @Override
    public SysUser getUser(String username) {
        return sysuserDao.getUserByName(username);
    }

    /**
     * 查询用户的所有菜单ID
     */
    @Override
    public List<String> queryAllMenuId(String userId) {
        return sysuserDao.queryAllMenuId(userId);
    }

    @Override
    public Page<SysUser> queryPageList(Page<SysUser> page, Map<String, Object> map) {
        page.setRecords(sysuserDao.queryPageList(page, map));
        return page;
    }

    @Override
    public List<SysUser> queryList(Map<String, Object> map) {
        return sysuserDao.queryList(map);
    }

    @Override
    public List<String> queryAllPerms(String userId) {
        return sysuserDao.queryAllPerms(userId);
    }

    @Override
    public SysUser queryByUserName(String username) {
        return sysuserDao.queryByUserName(username);
    }

    @Override
    @Transactional
    public void deleteBatch(String[] userId) {
        sysuserDao.deleteSysUserBatch(userId);
        sysuserDao.deleteSysUserRoleBatch(userId);
    }

    @Override
    @Transactional
    public void save(SysUser user) {
        user.setCreateTime(new Date());
        // sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        sysuserDao.insert(user);

        // 保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional
    public void update(SysUser user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        sysuserDao.updateById(user);

        // 先删除原来用户与角色的关系
        sysUserRoleService.deleteByUserId(user.getUserId());
        // 保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public List<SysUser> deptUserList(Map<String, Object> map) {
        return sysuserDao.deptUserList(map);
    }

    @Override
    public SysUser selectByName(String name) {
        return sysuserDao.selectByName(name);
    }

    @Override
    public SysUser findById(String id) {
        return sysuserDao.findById(id);
    }

    @Override
    public List<SysUser> selectByPid(List<String> list) {
        return sysuserDao.selectByPid(list);
    }

    @Override
    public List<SysUser> selectUserList(String[] ids) {
        return sysuserDao.selectUserList(ids);
    }

    @Override
    public List<Map<String, Object>> queryUserByDeptId(String deptId) {
        return sysuserDao.queryUserByDeptId(deptId);
    }

    @Override
    public String getAvatar(String id) {
        return sysuserDao.getAvatar(id);
    }

    @Override
    public List<Map<String, Object>> getUserListByRoles(String deptId) {
        return sysuserDao.getUserListByRoles(deptId);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysuserDao.selectAll();
    }

    @Override
    public Page<SysUser> pageByDeptId(Page<SysUser> pageUtil, Map<String, Object> map) {
        pageUtil.setRecords(sysuserDao.pageByDeptId(map));
        return pageUtil;
    }

    @Override
    public String getUserNames(String userids) {
        String str = "";
        if (StringUtils.isNotBlank(userids)) {
            for (String userid : userids.split(",")) {
                SysUser user = sysuserDao.findById(userid);
                if (null != user) {
                    str += user.getUsername() + ",";
                }
            }
        }
        if (str.length() > 1) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    @Override
    public List<String> queryRoleByUserId(String userId) {
        return sysuserDao.queryRoleByUserId(userId);
    }

}
