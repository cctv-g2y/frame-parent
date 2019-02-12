package com.makun.service.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.system.SysRole;

/**
 * @说明：[系统角色service]
 * @author：makun
 */
public interface SysRoleService extends IService<SysRole> {

    public Page<SysRole> queryPageList(Page<SysRole> pageUtil, Map<String, Object> map);

    public List<SysRole> queryList(Map<String, Object> map);

    public void deleteBatch(String[] roleIds);

    public void save(SysRole role);

    public void update(SysRole role);

}
