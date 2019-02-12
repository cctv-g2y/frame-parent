package com.makun.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.makun.config.base.UserUtils;
import com.makun.entity.system.SysUser;
import com.makun.service.system.SysUserService;
import com.makun.utils.R;

/**
 * @说明：用户登录控制层
 * @author: makun
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysUserService sysuserService;

    /**
     * 查询所有用户列表
     * 
     * @return
     */
    @RequestMapping("/showAlluser")
    public Map<String, Object> showAlluser() {
        List<SysUser> list = sysuserService.findAlluser();
        Map<String, Object> map = new HashMap<>();
        map.put("records", list);
        return map;
    }

    /**
     * 登录
     */
    @RequestMapping("/init")
    public R login(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = sysUser.getUsername().replaceAll(" +", "");
            String password = sysUser.getPassword();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(token);
            // 将当前登录用户放入session
            SysUser user = sysuserService.getUser(username);
            request.getSession().setAttribute("user", user);
            return R.ok().put("status", 200).put("message", "登录成功");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(e.getMessage()).put("status", 500).put("message", e.getMessage());
        }
    }

    /**
     * 系统主页
     */
    @RequestMapping("/index")
    public ModelAndView sysIndex() {
        ModelAndView model = new ModelAndView("index");
        String username = UserUtils.getUser().getUsername();
        String filepath = UserUtils.getUser().getUserPhoto();
        if (username.equals("admin")) {
            model.addObject("defalutpage", "sysindex");
        } else {
            model.addObject("defalutpage", "userindex");
        }
        model.addObject("username", username);
        model.addObject("filepath", filepath);
        return model;
    }

    /**
     * 管理员主页
     */
    @RequestMapping("/sysindex")
    public ModelAndView sysindex() {
        return new ModelAndView("sysindex");
    }

    /**
     * 用户主页
     */
    @RequestMapping("/userindex")
    public ModelAndView userindex() {
        return new ModelAndView("userindex");
    }

    /**
     * 退出登录
     */
    @RequestMapping("/goout")
    public ModelAndView goout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("login");
        request.getSession().invalidate();
        return model;
    }

}
