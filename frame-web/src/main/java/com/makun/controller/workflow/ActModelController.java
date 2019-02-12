package com.makun.controller.workflow;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.makun.config.base.UserUtils;
import com.makun.date.Constants;
import com.makun.entity.system.SysDict;
import com.makun.entity.system.SysRole;
import com.makun.entity.system.SysUser;
import com.makun.entity.workflow.Creatorconfine;
import com.makun.service.system.SysDictService;
import com.makun.service.system.SysRoleService;
import com.makun.service.system.SysUserService;
import com.makun.service.workflow.ActModelService;
import com.makun.service.workflow.CreatorconfineService;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;
import com.makun.utils.StringUtils;

/**
 * @说明:工作流模型控制层
 * @author makun
 */
@RestController
@RequestMapping("actmodel")
public class ActModelController {

    @Autowired
    private ActModelService actModelService;

    @Autowired
    private SysDictService sysDictservice;

    @Autowired
    private CreatorconfineService creatorconfineService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * @说明:模型列表页面
     */
    @RequestMapping("modellistpage")
    public ModelAndView modelListPage() {
        return new ModelAndView("modules/workflow/modellist");
    }

    /**
     * @说明:模型列表数据
     */
    @RequestMapping("listdata")
    public Map<String, Object> listData(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        return actModelService.modelList(query);
    }

    /**
     * @说明:保存模型
     */
    @RequestMapping("saveorupdate")
    public R saveModel(String name, String key, String description, String category) {
        Model createModel = null;
        try {
            createModel = actModelService.createModel(name, key, description, category);
            if (null != createModel) {
                return R.ok("保存成功!").put("modelId", createModel.getId());
            } else {
                return R.error("保存失败");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return R.error("保存失败");
        }
    }

    /**
     * @说明:删除Model
     */
    @RequestMapping("delete")
    public R delete(@RequestBody String[] ids) {
        return actModelService.delete(ids);
    }

    /**
     * @说明:导出Model的xml文件
     */
    @RequestMapping("exports")
    public R exports(String id, HttpServletResponse response) {
        return actModelService.exports(id, response);
    }

    /**
     * @说明:根据Model部署流程
     */
    @RequestMapping("deploys")
    public R deploys(String id) {
        String userId = UserUtils.getUserId();
        return actModelService.deploys(id,userId);
    }

    /**
     * @说明:获取流程模型分类
     */
    @RequestMapping("getmodelcategory")
    public List<SysDict> getModelCategory() {
        return sysDictservice.findListByPcode(Constants.ACT_CODE);
    }

    /**
     * @说明:获取用户列表
     */
    @RequestMapping("getuserlist")
    public List<SysUser> getUserList(@RequestParam Map<String, Object> params) {
        return sysUserService.queryList(params);
    }

    /**
     * @说明:获取角色列表
     */
    @RequestMapping("getrolelist")
    public List<SysRole> getRoleList(@RequestParam Map<String, Object> params) {
        return sysRoleService.queryList(params);
    }

    /**
     * @说明：保存model和控制人或者角色
     */
    @Transactional
    @RequestMapping("saveconfig")
    public R saveConfig(Creatorconfine creatorconfine) {
        String modelId = creatorconfine.getModelId();
        String id = creatorconfine.getId();
        Date nowTime = new Date();
        if (StringUtils.isBlank(modelId)) {
            return R.error("数据错误");
        }
        if (StringUtils.isBlank(creatorconfine.getUserIds()) && StringUtils.isBlank(creatorconfine.getRoleIds())) {
            return R.error("人员或者角色不能全部为空");
        }
        if (StringUtils.isBlank(id)) {
            creatorconfine.setCreateId(UserUtils.getUserId());
            creatorconfine.setCreateTime(nowTime);
            creatorconfine.setDelFlag("1");
            creatorconfineService.insert(creatorconfine);
        } else {
            creatorconfine.setUpdateId(UserUtils.getUserId());
            creatorconfine.setUpdateTime(nowTime);
            creatorconfine.setDelFlag("1");
            creatorconfineService.updateById(creatorconfine);
        }
        return R.ok("保存成功");
    }

    /**
     * @说明：展示model控制配置详情
     */
    @RequestMapping("showconfig")
    public Creatorconfine showConfig(String modelId) {
        List<String> userList = new ArrayList<String>();
        List<String> roleList = new ArrayList<String>();
        Creatorconfine creatorconfine = creatorconfineService.getByModelId(modelId);
        if (null == creatorconfine) {
            return new Creatorconfine();
        }
        String userIds = creatorconfine.getUserIds();
        String roleIds = creatorconfine.getRoleIds();
        if (StringUtils.isNotBlank(userIds)) {
            userList = Arrays.asList(userIds.split(","));
        }
        if (StringUtils.isNotBlank(roleIds)) {
            roleList = Arrays.asList(roleIds.split(","));
        }
        creatorconfine.setUserList(userList);
        creatorconfine.setRoleList(roleList);
        return creatorconfine;
    }

    /**
     * @说明:更新Model分类--暂未用到
     */
    @RequestMapping(value = "updatecategory")
    public R updateCategory(String id, String category) {
        return actModelService.updateCategory(id, category);
    }

}
