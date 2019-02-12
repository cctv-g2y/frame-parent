package com.makun.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.makun.config.base.UserUtils;
import com.makun.entity.system.SysRole;
import com.makun.service.system.SysRoleMenuService;
import com.makun.service.system.SysRoleService;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;

/**
 * @说明：系统角色controller
 * @author：makun
 */
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 角色管理页面跳转
     */
    @RequestMapping("/sysrole")
    public ModelAndView showList() {
        return new ModelAndView("modules/sys/role");
    }

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    public Page<SysRole> list(@RequestParam Map<String, Object> params) {
        // 如果不是超级管理员，则只查询自己创建的角色列表
        if (!"1".equals(UserUtils.getUserId())) {
            params.put("createUserId", UserUtils.getUserId());
        }
        // 查询列表数据
        QueryUtil query = new QueryUtil(params);
        Page<SysRole> pageUtil = new Page<SysRole>(query.getPage(), query.getLimit());
        Page<SysRole> page = sysRoleService.queryPageList(pageUtil, query);
        return page;
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    public R select() {
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果不是超级管理员，则只查询自己所拥有的角色列表
        if (!"1".equals(UserUtils.getUserId())) {
            map.put("createId", UserUtils.getUserId());
        }
        List<SysRole> list = sysRoleService.queryList(map);
        return R.ok().put("list", list);
    }

    /**
     * 角色信息
     */
    @RequestMapping("/infobyid")
    public R info(String roleId) {
        SysRole role = sysRoleService.selectById(roleId);

        // 查询角色对应的菜单
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        return R.ok().put("role", role);
    }

    /**
     * 保存角色
     */
    @RequestMapping("/save")
    public R save(SysRole role) {
        int roleCount = sysRoleService.selectCount(new EntityWrapper<SysRole>().eq("ROLE_NAME", role.getRoleName()));
        if (roleCount > 0) {
            return R.error("角色已存在");
        }
        try {
            role.setCreateId(UserUtils.getUserId());
            role.setCreateTime(new Date());
            role.setDeleteFlag("1");
            sysRoleService.save(role);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("添加失败");
        }
        return R.ok("添加成功");

    }

    /**
     * 修改角色
     */
    @RequestMapping("/update")
    public R update(SysRole role) {
        SysRole sysRole = sysRoleService.selectById(role.getRoleId());
        if (!role.getRoleName().equals(sysRole.getRoleName())) {
            int roleCount = sysRoleService
                    .selectCount(new EntityWrapper<SysRole>().eq("ROLE_NAME", role.getRoleName()));
            if (roleCount > 0) {
                return R.error("角色已存在");
            }
        }
        try {
            sysRoleService.update(role);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("修改失败");
        }
        return R.ok("修改成功");
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] roleIds) {
        try {
            sysRoleService.deleteBatch(roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("删除失败");
        }
        return R.ok("删除成功");
    }

}
