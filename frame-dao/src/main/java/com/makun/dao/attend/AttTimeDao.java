package com.makun.dao.attend;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.makun.entity.attend.AttendTime;

/**
 * @说明：[考勤时间dao接口层]
 * @author makun
 */
public interface AttTimeDao extends BaseMapper<AttendTime> {

    /**
     * 查询已经设置的考勤时间
     * 
     * @return AttendTime
     */
    public AttendTime getTime();

    /**
     * 清空考勤时间点的设置
     */
    public void flushall();

}
