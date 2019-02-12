package com.makun.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.makun.config.base.UserUtils;
import com.makun.entity.system.SysDept;
import com.makun.service.system.SysDeptService;
import com.makun.utils.R;

/**
 * @说明：系统部门controller
 * @author：makun
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    // @Autowired
    // private sysUserService sysUserService;

    /**
     * 部门管理页面跳转
     */
    @RequestMapping("/sysdept")
    public ModelAndView sysdept() {
        return new ModelAndView("modules/sys/dept");
    }

    /**
     * 选择部门(添加、修改菜单)]
     */
    @RequestMapping("/select")
    public R select() {
        List<SysDept> deptList = sysDeptService.queryList(new HashMap<String, Object>());

        // // 添加一级部门
        // if (getUserId().equals(Constant.SUPER_ADMIN))
        // {
        // SysDept root = new SysDept();
        // root.setDeptId("0");
        // root.setName("一级部门");
        // root.setParentId("-1");
        // root.setOpen(true);
        // deptList.add(root);
        // }

        return R.ok().put("deptList", deptList);
    }

    /**
     * 部门详情
     */
    @RequestMapping("/getdept")
    public R info(String deptId) {
        SysDept dept = sysDeptService.selectById(deptId);
        SysDept pdept = sysDeptService.selectById(dept.getParentId());
        dept.setParentName(pdept.getDeptName());
        return R.ok().put("dept", dept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(SysDept dept) {
        if (null == dept) {
            return R.error("数据错误");
        } else {
            if (null == dept.getDeptId() || "".equals(dept.getDeptId())) {
                dept.setDeptId(UUID.randomUUID().toString().replaceAll("-", ""));
            }
            dept.setCreateId(UserUtils.getUserId());
            dept.setCreateTime(new Date());
            dept.setDeleteFlag("1");
        }
        if (sysDeptService.insert(dept)) {
            return R.ok("添加成功");
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(SysDept dept) {
        if (null == dept) {
            return R.error("数据错误");
        } else {
            dept.setUpdateId(UserUtils.getUserId());
            dept.setUpdateTime(new Date());
        }
        if (sysDeptService.updateById(dept)) {
            return R.ok("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(String deptId) {
        // 判断是否有子部门
        List<String> deptList = sysDeptService.queryDetpIdList(deptId);
        if (deptList.size() > 0) {
            return R.error("请先删除子部门");
        }
        // 逻辑删除
        SysDept dept = new SysDept();
        dept.setDeptId(deptId);
        dept.setDeleteFlag("0");
        if (sysDeptService.updateById(dept)) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

}
