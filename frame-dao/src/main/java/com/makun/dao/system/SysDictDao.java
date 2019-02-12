package com.makun.dao.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.makun.entity.system.SysDict;

/**
 * @说明：字典管理接口
 * @author makun
 */
public interface SysDictDao extends BaseMapper<SysDict> {

    public List<SysDict> queryPageList(Page<SysDict> page, Map<String, Object> map);

    public List<SysDict> queryList(Map<String, Object> map);

    public String maxcode(String id);

    public List<String> queryIdForPid(String id);

    public List<SysDict> findListByPcode(String pCode);

}
