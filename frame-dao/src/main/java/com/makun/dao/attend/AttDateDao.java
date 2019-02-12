package com.makun.dao.attend;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.makun.entity.attend.AttendDate;

/**
 * @说明：[个人考勤Dao层]
 * @author makun
 */
public interface AttDateDao extends BaseMapper<AttendDate> {

    /**
     * 查询所有日期
     * 
     * @param page
     * @param map
     * @return List
     */
    public List<AttendDate> showattDate(Pagination page, Map<String, Object> map);

    /**
     * 根据日期判断该日期有没有重复添加
     * 
     * @param calendarDate
     * @return AttendDate
     */
    public AttendDate getDateByDate(String calendarDate);

    /**
     * 删除日期
     * 
     * @param attDateid
     * @return int
     */
    public int deleteDate(Object[] attDateid);

    /**
     * 判断当天是否是工作日
     * 
     * @param calendarDate
     * @return boolean
     */
    public String isattend(LocalDate calendarDate);

}
