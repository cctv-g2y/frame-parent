package com.makun.dao.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.makun.entity.system.SysRole;

/**
 * @说明：[系统角色dao]
 * @author：makun
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    public List<SysRole> queryPageList(Page<SysRole> page, Map<String, Object> map);

    public List<SysRole> queryList(Map<String, Object> map);

    public int deleteSysRoleByRoleId(Object[] id);

    public int deleteSysRoleMenuByRoleId(Object[] id);

    public int deleteSysUserRoleByRoleId(Object[] id);

}
