package com.makun.service.attend;

import java.time.LocalDate;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.makun.entity.attend.AttendDate;
import com.makun.utils.R;

/**
 * @说明：[个人考勤service层]
 * @author makun
 */
public interface AttDateService extends IService<AttendDate> {

    /**
     * 查看所有的日期
     * 
     * @param page
     * @param map
     * @return Page
     */
    public Page<AttendDate> showPageList(Page<AttendDate> page, Map<String, Object> map);

    /**
     * 根据id查询日期详情
     * 
     * @param attDateid
     * @return AttendDate
     */
    public AttendDate getattDate(String attDateid);

    /**
     * 根据日期判断该日期有没有重复添加
     * 
     * @param calendarDate
     * @return AttendDate
     */
    public AttendDate getattDateByDate(String calendarDate);

    /**
     * 添加日期
     * 
     * @param AttendDate
     * @return boolean
     */
    public boolean saveDate(AttendDate attenddate);

    /**
     * 根据id修改日期
     * 
     * @param attDateid
     * @return boolean
     */
    public boolean upDate(AttendDate attenddate);

    /**
     * 删除日期
     * 
     * @param attDateid
     * @return boolean
     */
    public boolean deleteDate(String[] attDateid);

    /**
     * 判断是否是工作日
     * 
     * @param workdate
     * @return boolean
     */
    public R isattend(LocalDate workDate);

}
