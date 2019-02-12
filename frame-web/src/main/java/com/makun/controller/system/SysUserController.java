package com.makun.controller.system;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.makun.config.base.UserUtils;
import com.makun.entity.system.SysUser;
import com.makun.excelutils.ExcelTemplate;
import com.makun.excelutils.ExcelUtil;
import com.makun.service.system.SysDeptService;
import com.makun.service.system.SysUserRoleService;
import com.makun.service.system.SysUserService;
import com.makun.utils.Assert;
import com.makun.utils.DateUtils;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;
import com.makun.utils.FinalData;

/**
 * @说明：系统用户controller
 * @author：makun
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 用户管理主页
     */
    @RequestMapping("/sysuser")
    public ModelAndView showList() {
        return new ModelAndView("modules/sys/user");
    }

    /**
     * 用户列表
     */
    @RequestMapping("/list")
    public Page<SysUser> list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        QueryUtil query = new QueryUtil(params);
        Page<SysUser> pageUtil = new Page<SysUser>(query.getPage(), query.getLimit());
        Page<SysUser> page = sysUserService.queryPageList(pageUtil, query);
        return page;
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("/userlist")
    public List<SysUser> userlist(@RequestParam Map<String, Object> params) {
        return sysUserService.queryList(params);
    }

    /**
     * 用户信息
     */
    @RequestMapping("/infobyid")
    public R info(String userId) {
        SysUser user = sysUserService.selectById(userId);
        // 获取用户所属的角色列表
        List<String> roleIdList = sysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);
        return R.ok().put("user", user);
    }

    /**
     * 修改登录用户密码
     */
    @RequestMapping("/updatepassword")
    public R password(String newPassword) {
        Assert.isBlank(newPassword, "新密码不为能空");
        // sha256加密
        newPassword = new Sha256Hash(newPassword, UserUtils.getUser().getSalt()).toHex();
        SysUser user = UserUtils.getUser();
        user.setPassword(newPassword);
        // 更新密码
        if (!sysUserService.updateById(user)) {
            return R.error("修改失败");
        }
        return R.ok("修改成功");
    }

    /**
     * 校验原密码
     */
    @RequestMapping("/checkpassword")
    public Map<String, Boolean> checkPwd(String password) {
        boolean result = false;
        Map<String, Boolean> map = new HashMap<>();
        password = new Sha256Hash(password, UserUtils.getUser().getSalt()).toHex();
        if (UserUtils.getUser().getPassword().equals(password)) {
            result = true;
        }
        map.put("valid", result);
        return map;
    }

    /**
     * 保存用户
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysUser user) {
        if (null == user) {
            return R.error("数据异常");
        }
        // 新增用户是否已存在
        if (sysUserService.queryByUserName(user.getUsername()) != null) {
            return R.error("用户已存在");
        }
        user.setCreateTime(new Date());
        user.setCreateId(UserUtils.getUserId());
        user.setStatus("1");
        user.setDeleteFlag("1");
        try {
            sysUserService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("添加失败");
        }
        return R.ok("添加成功");
    }

    /**
     * 修改用户
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysUser user) {
        if (null == user) {
            return R.error("数据异常");
        }
        user.setUpdateId(UserUtils.getUserId());
        user.setUpdateTime(new Date());
        try {
            sysUserService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("修改失败");
        }
        return R.ok("修改成功");
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] userIds) {
        if (null == userIds || null != userIds && userIds.length == 0) {
            return R.error("数据异常");
        }
        if (ArrayUtils.contains(userIds, FinalData.ADMIN_ID)) {
            return R.error("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, UserUtils.getUserId())) {
            return R.error("不能删除自己");
        }
        try {
            sysUserService.deleteBatch(userIds);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("删除失败");
        }
        return R.ok("删除成功");
    }

    /**
     * 导出用户
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        List<SysUser> userList = sysUserService.queryList(params);
        System.out.println(userList);
        OutputStream os = response.getOutputStream();
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", "用户信息表");
        map.put("total", userList.size() + " 条");
        map.put("date", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
        // 响应信息，弹出文件下载窗口
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("用户信息表.xls", "UTF-8"));
        ExcelTemplate et = ExcelUtil.getInstance().handlerObj2Excel("web-info-template.xls", userList, SysUser.class,
                true);
        et.replaceFinalData(map);
        et.wirteToStream(os);
        os.flush();
        os.close();
    }

    @RequestMapping("/getusernames")
    public R getUserNames(String userIds) {
        if (null == userIds || "".equals(userIds)) {
            return R.ok();
        }
        String names = "";
        String[] ids = userIds.split(",");
        if (null != ids && ids.length > 0) {
            for (String id : ids) {
                SysUser user = sysUserService.findById(id);
                names += user.getUsername() + ",";
            }
            names = names.substring(0, names.length() - 1);
        }
        return R.ok().put("names", names);
    }

    @RequestMapping("/getlist")
    public Page<SysUser> getUserList(@RequestParam Map<String, Object> params) {
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(params.get("deptId")));
        List<String> idlist = new ArrayList<>();
        sysDeptService.getDeptTreeList(list, idlist);
        QueryUtil query = new QueryUtil(params);
        query.put("list", idlist);
        Page<SysUser> pageUtil = new Page<SysUser>(query.getPage(), query.getLimit());
        Page<SysUser> page = sysUserService.pageByDeptId(pageUtil, query);
        return page;
    }

    @RequestMapping("/getuserlist")
    public R getList(String deptid) {

        List<String> list = new ArrayList<>();
        list.add(deptid);
        List<String> idlist = new ArrayList<>();

        sysDeptService.getDeptTreeList(list, idlist);
        List<SysUser> userlist = sysUserService.selectByPid(idlist);
        return R.ok().put("list", userlist);
    }

    @RequestMapping("/getlistbyroles")
    public List<Map<String, Object>> getUserListByRoles(String roleids) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (null != roleids) {
            String[] roles = roleids.split(",");
            for (String roleid : roles) {
                list.addAll(sysUserService.getUserListByRoles(roleid));
            }
        }
        return list;
    }

}
