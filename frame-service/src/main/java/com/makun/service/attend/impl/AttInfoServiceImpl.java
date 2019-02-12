package com.makun.service.attend.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.attend.AttInfoDao;
import com.makun.entity.attend.AttendInfo;
import com.makun.service.attend.AttInfoService;

/**
 * @说明：[个人签到详情预览service实现层]
 * @author makun
 */
@Service
public class AttInfoServiceImpl extends ServiceImpl<AttInfoDao, AttendInfo> implements AttInfoService {

    @Autowired
    private AttInfoDao attinfoDao;

    /**
     * 查看个人签到列表预览
     */
    @Override
    public Page<AttendInfo> getMyattend(Page<AttendInfo> page, Map<String, Object> map) {
        List<AttendInfo> records = attinfoDao.selectPageList(page, map);
        page.setRecords(records);
        return page;
    }

    /**
     * 查看个人签到详情
     */
    @Override
    public AttendInfo goattend(Map<String, Object> map) {
        return attinfoDao.getmyattend(map);
    }

}
