package com.makun.service.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.system.SysDict;

/**
 * @说明：字典管理服务层
 * @author makun
 */
public interface SysDictService extends IService<SysDict> {

    public SysDict queryDictById(String id);

    public Page<SysDict> queryPageList(Page<SysDict> pageUtil, Map<String, Object> map);

    public int saveDict(SysDict sysDict);

    public int delDictById(String id);

    public List<SysDict> queryList(Map<String, Object> params);

    public List<SysDict> queryListByPid(String parentId);

    public List<SysDict> findListByPcode(String pCode);

    public SysDict findDictByCode(String code);

    public String initcode(String id);

}
