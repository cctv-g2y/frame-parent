package com.makun.entity.system;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @说明：[字典管理实体类]
 * @author: makun
 */
@TableName("tab_sys_dictionary")
public class SysDict extends Model<SysDict> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 字典id
    @TableId(value = "id", type = IdType.UUID)
    private String dicId;

    // 编码
    @TableField("code")
    private String code;

    // 名称
    @NotBlank(message = "名称不能为空")
    @TableField("name")
    private String name;

    // 排序号
    @TableField("sorting")
    private Integer sorting;

    // 父Id
    @TableField("parent_id")
    private String parentId;

    // 参数
    @TableField("params")
    private String params;

    // 值
    @TableField("value")
    @NotBlank(message = "值不能为空")
    private String value;

    // 描述
    @TableField("remark")
    private String remark;

    // 编辑状态
    @TableField("edit_state")
    private Integer editState;

    // ztree属性
    @TableField(exist = false)
    private boolean isParent;

    // 创建日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private Date createTime;

    // 修改日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    // 创建人
    @TableField("create_id")
    private String createId;

    // 修改人
    @TableField("update_id")
    private String updateId;

    public String getDicId() {
        return dicId;
    }

    public void setDicId(String dicId) {
        this.dicId = dicId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEditState() {
        return editState;
    }

    public void setEditState(Integer editState) {
        this.editState = editState;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean isParent) {
        this.isParent = isParent;
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

    @Override
    public String toString() {
        return "sysDict [dicId=" + dicId + ", code=" + code + ", name=" + name + ", sorting=" + sorting + ", parentId="
                + parentId + ", params=" + params + ", value=" + value + ", remark=" + remark + ", editState="
                + editState + ", isParent=" + isParent + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", createId=" + createId + ", updateId=" + updateId + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.dicId;
    }

}
