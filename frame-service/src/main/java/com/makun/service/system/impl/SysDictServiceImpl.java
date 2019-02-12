package com.makun.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.system.SysDictDao;
import com.makun.entity.system.SysDict;
import com.makun.service.system.SysDictService;
import com.makun.utils.StringUtils;

/**
 * @说明：字典管理服务层实现
 * @author makun
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDict> implements SysDictService {

    @Autowired
    private SysDictDao sysDictDao;

    /** 通过id获取字典 */
    @Override
    public SysDict queryDictById(String id) {
        return sysDictDao.selectById(id);
    }

    /** 字典集分页 */
    @Override
    public Page<SysDict> queryPageList(Page<SysDict> pageUtil, Map<String, Object> map) {
        pageUtil.setRecords(sysDictDao.queryPageList(pageUtil, map));
        return pageUtil;
    }

    /** 保存或者修改字典 */
    @Override
    @CacheEvict(value = "SysDict", allEntries = true)
    public int saveDict(SysDict sysDict) {
        if (StringUtils.isNotBlank(sysDict.getDicId())) {
            return sysDictDao.updateById(sysDict);
        } else {
            sysDict.setDicId(StringUtils.getUUID());
            return sysDictDao.insert(sysDict);
        }
    }

    /** 删除字典 */
    @Override
    @CacheEvict(value = "SysDict", allEntries = true)
    public int delDictById(String id) {
        List<String> idList = sysDictDao.queryIdForPid(id);
        if (idList != null && !idList.isEmpty()) {
            sysDictDao.deleteBatchIds(idList);
        }
        return sysDictDao.deleteById(id);
    }

    /** 字典集 */
    @Override
    public List<SysDict> queryList(Map<String, Object> params) {
        return sysDictDao.queryList(params);
    }

    /** 通过父id获取子节点 */
    @Override
    @Cacheable(value = "SysDict", key = "#parentId")
    public List<SysDict> queryListByPid(String parentId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parent_id", parentId);
        return sysDictDao.selectByMap(params);
    }

    /** 通过父编码获取子节点 */
    @Override
    @Cacheable(value = "SysDict", key = "#pCode")
    public List<SysDict> findListByPcode(String pCode) {
        return sysDictDao.findListByPcode(pCode);
    }

    /** 通过编码获取字典 */
    @Override
    public SysDict findDictByCode(String code) {
        SysDict dict = new SysDict();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        List<SysDict> list = sysDictDao.selectByMap(params);
        if (list != null && !list.isEmpty()) {
            dict = list.get(0);
        }
        return dict;
    }

    /** 初始化编码 */
    @Override
    public String initcode(String id) {
        String code = null;
        // 字典节点为'全部'
        if ("0".equals(id)) {
            code = sysDictDao.maxcode(null);
            if (StringUtils.isBlank(code)) {
                return "001";
            }
        } else {
            code = sysDictDao.maxcode(id);
            if (StringUtils.isBlank(code)) {
                return sysDictDao.selectById(id).getCode() + "001";
            }
        }
        return getCode(code);
    }

    /** 获取编码 */
    private String getCode(String code) {
        String str = code.substring(code.length() - 3);
        int count = Integer.valueOf(str) + 1;
        str = designCode(count);
        code = code.substring(0, code.length() - 3) + str;
        return code;
    }

    /** 构造编码 */
    private String designCode(int code) {
        String str = "000";
        int len = String.valueOf(code).length();
        if (len > str.length()) {
            return String.valueOf(code);
        }
        str = str.substring(len) + code;
        return str;
    }

}
