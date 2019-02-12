package com.makun.entity.attend;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @说明：[考勤日期实体类]
 * @author makun
 */
@TableName("tab_attend_date")
public class AttendDate extends Model<AttendDate> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 考勤日期id
    @TableId(value = "id", type = IdType.UUID)
    private String attDateid;

    // 所有日期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField("calendar_date")
    private Date calendarDate;

    // 是否工作日(1.是--0.不是)
    @TableField("is_workingday")
    private String isWorkingday;

    // 删除标记(0.删除--1.正常)
    @TableField("delete_flag")
    private String deleteFlag;

    // 备注
    @TableField("mark")
    private String mark;

    public String getAttDateid() {
        return attDateid;
    }

    public void setAttDateid(String attDateid) {
        this.attDateid = attDateid;
    }

    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    public String getIsWorkingday() {
        return isWorkingday;
    }

    public void setIsWorkingday(String isWorkingday) {
        this.isWorkingday = isWorkingday;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    protected Serializable pkVal() {
        return this.attDateid;
    }

}
