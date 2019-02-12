package com.makun.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.plugins.Page;
import com.makun.config.base.UserUtils;
import com.makun.entity.system.SysDict;
import com.makun.service.system.SysDictService;
import com.makun.utils.QueryUtil;
import com.makun.utils.R;
import com.makun.utils.StringUtils;

/**
 * @说明：字典管理控制层
 * @author makun
 */
@RestController
@RequestMapping("/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /** 字典管理首页 */
    @RequestMapping("/showindex")
    public ModelAndView showIndex() {
        return new ModelAndView("modules/sys/dict");
    }

    /** 通过id获取字典 */
    @RequestMapping("/dictbyid")
    public ModelAndView queryDictById(String id) {
        SysDict dict = new SysDict();
        ModelAndView model = new ModelAndView("modules/sys/dictform");
        if (StringUtils.isNotBlank(id)) {
            dict = sysDictService.queryDictById(id);
        }
        model.addObject("dict", dict);
        return model;
    }

    /** 获取字典集 */
    @RequestMapping("/dictlist")
    public List<SysDict> getDicts(@RequestParam Map<String, Object> params) {
        return sysDictService.queryList(params);
    }

    /** 获取字典集分页 */
    @RequestMapping("/querydictpage")
    public Page<SysDict> queryDictPageList(@RequestParam Map<String, Object> params) {
        QueryUtil query = new QueryUtil(params);
        Page<SysDict> pageUtil = new Page<SysDict>(query.getPage(), query.getLimit());
        Page<SysDict> page = sysDictService.queryPageList(pageUtil, params);
        return page;
    }

    /** 保存字典 */
    @RequestMapping("/savedict")
    public R saveDict(SysDict sysDict) {
        if (StringUtils.isBlank(sysDict.getDicId())) {
            sysDict.setCreateTime(new Date());
            sysDict.setCreateId(UserUtils.getUserId());
            sysDict.setEditState(1);
        } else {
            sysDict.setParentId(sysDictService.queryDictById(sysDict.getDicId()).getParentId());
            sysDict.setUpdateTime(new Date());
            sysDict.setUpdateId(UserUtils.getUserId());
            sysDict.setEditState(2);
        }
        int flag = sysDictService.saveDict(sysDict);
        if (flag == 1) {
            return R.ok("保存成功").put("dict", sysDict);
        } else {
            return R.error("保存失败");
        }
    }

    /** 删除字典 */
    @RequestMapping("/deldict")
    public R delDict(String id) {
        int flag = sysDictService.delDictById(id);
        if (flag == 1) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /** 获取编码 */
    @RequestMapping("/initcode")
    public Map<String, String> initcode(String dicId) {
        Map<String, String> map = new HashMap<String, String>();
        String code = sysDictService.initcode(dicId);
        map.put("code", code);
        return map;
    }

}
