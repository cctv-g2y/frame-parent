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
 * @说明：[考勤时间实体类]
 * @author makun
 */
@TableName("tab_attend_time")
public class AttendTime extends Model<AttendTime> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 考勤时间id
    @TableId(value = "id", type = IdType.UUID)
    private String attTimeid;

    // 上班时间
    @TableField("on_time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date onTime;

    // 下班时间
    @TableField("off_time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date offTime;

    // 删除标记
    @TableField("delete_flag")
    private String deleteFlag;

    // 备注
    @TableField("mark")
    private String mark;

    public String getAttTimeid() {
        return attTimeid;
    }

    public void setAttTimeid(String attTimeid) {
        this.attTimeid = attTimeid;
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
        return this.attTimeid;
    }

}
