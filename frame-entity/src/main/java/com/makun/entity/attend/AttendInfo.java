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
 * @说明：[人员考勤信息实体类]
 * @author makun
 */
@TableName("tab_attend_info")
public class AttendInfo extends Model<AttendInfo> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 考勤id
    @TableId(value = "id", type = IdType.UUID)
    private String attendId;

    // 用户id
    @TableField("user_id")
    private String userId;

    // 用户名
    @TableField(exist = false)
    private String userName;

    // 实际考勤日
    @TableField("work_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workDate;

    // 应该考勤日
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date shouldDate;

    // 迟到时间
    @TableField("late_time")
    private String lateTime;

    // 早退时间
    @TableField("early_time")
    private String earlyTime;

    // 实际上班考勤时间
    @TableField("on_duty_time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date onTime;

    // 实际下班考勤时间
    @TableField("off_duty_time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date offTime;

    // 上班考勤状态
    @TableField("on_status")
    private String onStatus;

    // 下班考勤状态
    @TableField("off_status")
    private String offStatus;

    // 迟到说明
    @TableField("late_mark")
    private String lateMark;

    // 早退说明
    @TableField("early_mark")
    private String earlyMark;

    // 删除标记(0.删除--1.正常)
    @TableField("delete_flag")
    private String deleteFlag;

    // 缺勤说明
    @TableField("mark")
    public String mark;

    public String getAttendId() {
        return attendId;
    }

    public void setAttendId(String attendId) {
        this.attendId = attendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Date getShouldDate() {
        return shouldDate;
    }

    public void setShouldDate(Date shouldDate) {
        this.shouldDate = shouldDate;
    }

    public String getLateTime() {
        return lateTime;
    }

    public void setLateTime(String lateTime) {
        this.lateTime = lateTime;
    }

    public String getEarlyTime() {
        return earlyTime;
    }

    public void setEarlyTime(String earlyTime) {
        this.earlyTime = earlyTime;
    }

    public Date getOnTime() {
        return onTime;
    }

    public void setOnTime(Date onTime) {
        this.onTime = onTime;
    }

    public Date getOffTime() {
        return offTime;
    }

    public void setOffTime(Date offTime) {
        this.offTime = offTime;
    }

    public String getOnStatus() {
        return onStatus;
    }

    public void setOnStatus(String onStatus) {
        this.onStatus = onStatus;
    }

    public String getOffStatus() {
        return offStatus;
    }

    public void setOffStatus(String offStatus) {
        this.offStatus = offStatus;
    }

    public String getLateMark() {
        return lateMark;
    }

    public void setLateMark(String lateMark) {
        this.lateMark = lateMark;
    }

    public String getEarlyMark() {
        return earlyMark;
    }

    public void setEarlyMark(String earlyMark) {
        this.earlyMark = earlyMark;
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

    /**
     * @author makun
     */
    @Override
    protected Serializable pkVal() {
        return this.attendId;
    }

}
