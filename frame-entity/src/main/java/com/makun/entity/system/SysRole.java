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
 * @说明：[系统角色实体类]
 * @author：makun
 */
@TableName("tab_sys_role")
public class SysRole extends Model<SysRole> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 角色id
    @TableId(value = "id", type = IdType.UUID)
    private String roleId;

    // 角色名称
    @TableField("role_name")
    private String roleName;

    // 权限id列表
    @TableField(exist = false)
    private List<String> menuIdList;

    // 备注
    @TableField("mark")
    private String mark;

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
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
        return "sysRole [roleId=" + roleId + ", roleName=" + roleName + ", menuIdList=" + menuIdList + ", mark=" + mark
                + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createId=" + createId + ", updateId="
                + updateId + ", createName=" + createName + ", updateName=" + updateName + ", deleteFlag=" + deleteFlag
                + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.roleId;
    }

}
