package com.makun.service.attend;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.attend.AttendInfo;

/**
 * @说明：[个人签到情况预览service层]
 * @author makun
 */
public interface AttInfoService extends IService<AttendInfo> {

    /**
     * 查看个人签到列表预览
     * 
     * @param page
     * @param map
     * @return Page
     */
    public Page<AttendInfo> getMyattend(Page<AttendInfo> page, Map<String, Object> map);

    /**
     * 查看个人签到详情
     * 
     * @param map
     * @return AttendInfo
     */
    public AttendInfo goattend(Map<String, Object> map);

}
