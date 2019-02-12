package com.makun.service.attend;

import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.attend.AttendTime;

/**
 * @说明：[考勤时间service层]
 * @author makun
 */
public interface AttTimeService extends IService<AttendTime> {

    /**
     * 查询已经设置好的考勤时间
     * 
     * @return AttendTime
     */
    public AttendTime getTime();

    /**
     * 新增考勤时间
     * 
     * @param attTime
     * @return boolean
     */
    public boolean saveTime(AttendTime attTime);

    /**
     * 根据id修改考勤时间
     * 
     * @param attTime
     * @return boolean
     */
    public boolean updateTime(AttendTime attTime);

    /**
     * 清空对考勤时间点的设置
     */
    public void flushall();

}
