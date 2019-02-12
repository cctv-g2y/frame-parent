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
 * @说明：[系统用户实体类]
 * @author：makun
 */
@TableName("tab_sys_user")
public class SysUser extends Model<SysUser> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 用户id
    @TableId(value = "id", type = IdType.UUID)
    private String userId;

    // 用户名
    @TableField("username")
    private String username;

    // 密码
    @TableField("password")
    private String password;

    // 盐
    @TableField("salt")
    private String salt;

    // 状态 0：禁用 1：正常
    @TableField("status")
    private String status;

    // 角色id列表
    @TableField(exist = false)
    private List<String> roleIdList;

    // 性别
    @TableField("sex")
    private String sex;

    // 电话号码
    @TableField("tel")
    private String tel;

    // 部门id
    @TableField("dept_id")
    private String deptId;

    // 部门名称
    @TableField(exist = false)
    private String deptName;

    // 电子邮箱
    @TableField("email")
    private String email;

    // 地址
    @TableField("address")
    private String address;

    // 个人头像
    @TableField(exist = false)
    private String userPhoto;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
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
        return "sysUser [userId=" + userId + ", username=" + username + ", password=" + password + ", salt=" + salt
                + ", status=" + status + ", roleIdList=" + roleIdList + ", sex=" + sex + ", tel=" + tel + ", deptId="
                + deptId + ", deptName=" + deptName + ", email=" + email + ", address=" + address + ", userPhoto="
                + userPhoto + ", createTime=" + createTime + ", updateTime=" + updateTime + ", createId=" + createId
                + ", updateId=" + updateId + ", createName=" + createName + ", updateName=" + updateName
                + ", deleteFlag=" + deleteFlag + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
