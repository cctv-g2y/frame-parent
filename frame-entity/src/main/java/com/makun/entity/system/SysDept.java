package com.makun.entity.system;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @说明：[系统部门实体类]
 * @author：makun
 */
@TableName("tab_sys_dept")
public class SysDept extends Model<SysDept> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // id
    @TableId(value = "id", type = IdType.UUID)
    private String deptId;

    // 上级部门id
    @TableField("parent_id")
    private String parentId;

    // 部门名称
    @TableField("dept_name")
    private String deptName;

    // 排序号
    @TableField("order_num")
    private Integer orderNum;

    // 上级部门名称
    @TableField(exist = false)
    private String parentName;

    // ztree属性
    @TableField(exist = false)
    private Boolean open;

    // ztree属性
    @TableField(exist = false)
    private Boolean isParent = true;

    // ztree集合
    @TableField(exist = false)
    private List<?> list;

    // 创建时间
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    // 修改时间
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    // 创建人id
    @TableField("create_id")
    private String createId;

    // 修改人id
    @TableField("update_id")
    private String updateId;

    // 创建人姓名
    @TableField(exist = false)
    private String createName;

    // 修改人姓名
    @TableField(exist = false)
    private String updateName;

    // 删除标记
    @TableField("delete_flag")
    private String deleteFlag;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "sysDept [deptId=" + deptId + ", parentId=" + parentId + ", deptName=" + deptName + ", orderNum="
                + orderNum + ", parentName=" + parentName + ", open=" + open + ", isParent=" + isParent + ", list="
                + list + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createId=" + createId
                + ", updateId=" + updateId + ", createName=" + createName + ", updateName=" + updateName
                + ", deleteFlag=" + deleteFlag + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }

}
