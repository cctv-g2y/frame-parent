package com.makun.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.makun.config.base.UserUtils;
import com.makun.entity.system.SysMenu;
import com.makun.service.system.SysMenuService;
import com.makun.utils.R;

/**
 * @说明：系统菜单controller
 * @author：makun
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 跳转菜单管理界面
     */
    @RequestMapping("/sysmenu")
    public ModelAndView sysmenu() {
        ModelAndView model = new ModelAndView("modules/sys/menu");
        return model;
    }

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    public List<SysMenu> list() {
        List<SysMenu> menuList = sysMenuService.queryList(new HashMap<String, Object>());
        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    public List<SysMenu> select() {
        // 查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();
        // 添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId("0");
        root.setMenuName("一级菜单");
        root.setParentId("-1");
        root.setParentName("一级菜单");
        root.setOpen(true);
        menuList.add(root);
        return menuList;
    }

    /**
     * 导航菜单
     */
    @RequestMapping("/menuload")
    public List<SysMenu> nav() {
        return sysMenuService.getUserMenuList(UserUtils.getUserId());
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/infobyid")
    public SysMenu info(String menuId) {
        return sysMenuService.selectById(menuId);
    }

    /**
     * 保存菜单
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysMenu menu) {
        if (null == menu) {
            return R.error("数据异常");
        }
        menu.setCreateId(UserUtils.getUserId());
        menu.setCreateTime(new Date());
        menu.setDeleteFlag("1");
        if (sysMenuService.insert(menu)) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysMenu menu) {
        if (null == menu) {
            return R.error("数据异常");
        }
        menu.setUpdateId(UserUtils.getUserId());
        menu.setUpdateTime(new Date());
        if (sysMenuService.updateById(menu)) {
            return R.ok("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(String menuId) {
        if (menuId.equals("5")) {
            return R.error("系统菜单，不能删除");
        }

        // 判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if (menuList.size() > 0) {
            return R.error("请先删除子菜单或按钮");
        }

        try {
            sysMenuService.delById(menuId);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("删除失败");
        }
        return R.ok("删除成功");
    }

}
