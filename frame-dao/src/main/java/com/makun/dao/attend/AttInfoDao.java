package com.makun.dao.attend;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.makun.entity.attend.AttendInfo;

/**
 * @说明：[个人签到详情预览Dao层]
 * @author makun
 */
public interface AttInfoDao extends BaseMapper<AttendInfo> {

    /**
     * 获取个人签到详情并分页
     * 
     * @param page
     * @param map
     * @return List
     */
    public List<AttendInfo> selectPageList(Pagination page, Map<String, Object> map);

    /**
     * 获取个人签到详情
     * 
     * @param map
     * @return AttendInfo
     */
    public AttendInfo getmyattend(Map<String, Object> map);

}
