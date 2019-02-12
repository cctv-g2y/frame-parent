package com.makun.service.attend.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.makun.dao.attend.AttTimeDao;
import com.makun.entity.attend.AttendTime;
import com.makun.service.attend.AttTimeService;

/**
 * @说明：[考勤时间service实现层]
 * @author makun
 */
@Service
public class AttTimeServiceImpl extends ServiceImpl<AttTimeDao, AttendTime> implements AttTimeService {

    @Autowired
    private AttTimeDao attTimedao;

    /**
     * 查询已经设置好的考勤时间
     */
    @Override
    public AttendTime getTime() {
        return attTimedao.getTime();
    }

    /**
     * 新增考勤时间
     */
    @Override
    public boolean saveTime(AttendTime attTime) {
        boolean isSave = false;
        int intValue = attTimedao.insert(attTime).intValue();
        if (intValue > 0) {
            isSave = true;
        }
        return isSave;
    }

    /**
     * 根据id修改考勤时间
     */
    @Override
    public boolean updateTime(AttendTime attTime) {
        boolean isUpda = false;
        int intValue = attTimedao.updateById(attTime).intValue();
        if (intValue > 0) {
            isUpda = true;
        }
        return isUpda;
    }

    /**
     * 清空对考勤时间点的设置
     */
    @Override
    public void flushall() {
        attTimedao.flushall();
    }

}
