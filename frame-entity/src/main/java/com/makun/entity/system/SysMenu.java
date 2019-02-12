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
 * @说明：[系统菜单实体类]
 * @author：makun
 */
@TableName("tab_sys_menu")
public class SysMenu extends Model<SysMenu> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // id
    @TableId(value = "id", type = IdType.UUID)
    private String menuId;

    // 父级id
    @TableField("parent_id")
    private String parentId;

    // 菜单名称
    @TableField("menu_name")
    private String menuName;

    // 菜单url
    @TableField("url")
    private String url;

    // 授权(多个用逗号分隔，如：user:list,user:create)
    @TableField("perms")
    private String perms;

    // 类型 0：目录 1：菜单 2：按钮
    @TableField("type")
    private Integer type;

    // 菜单图标
    @TableField("icon")
    private String icon;

    // 排序号
    @TableField("order_num")
    private Integer orderNum;

    // ztree属性
    @TableField(exist = false)
    private Boolean open;

    // ztree集合
    @TableField(exist = false)
    private List<?> list;

    // 父级菜单名称
    @TableField(exist = false)
    private String parentName;

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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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
        return "sysMenu [menuId=" + menuId + ", parentId=" + parentId + ", menuName=" + menuName + ", url=" + url
                + ", perms=" + perms + ", type=" + type + ", icon=" + icon + ", orderNum=" + orderNum + ", open=" + open
                + ", list=" + list + ", parentName=" + parentName + ", createTime=" + createTime + ", updateTime="
                + updateTime + ", createId=" + createId + ", updateId=" + updateId + ", createName=" + createName
                + ", updateName=" + updateName + ", deleteFlag=" + deleteFlag + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
