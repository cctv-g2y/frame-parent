package com.makun.entity.workflow;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @说明：流程发起权限控制
 * @author makun
 */
@TableName("tab_workflow_creatorconfine")
public class Creatorconfine extends Model<Creatorconfine> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // id
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    // 模型id
    @TableField("model_id")
    private String modelId;

    // 流程key
    @TableField("process_key")
    private String processKey;

    // 角色ids支持多个角色用,分隔
    @TableField("role_ids")
    private String roleIds;

    // 用户ids支持多个用户用,分隔
    @TableField("user_ids")
    private String userIds;

    // 创建人
    @TableField("create_id")
    private String createId;

    // 创建时间
    @TableField("create_time")
    private Date createTime;

    // 修改人
    @TableField("update_id")
    private String updateId;

    // 修改时间
    @TableField("update_time")
    private Date updateTime;

    // 删除标记--0表示删除；1表示正常
    @TableField("del_flag")
    private String delFlag;

    // 该model拥有的用户集合
    @TableField(exist = false)
    public List<String> userList;

    // 该model拥有的角色集合
    @TableField(exist = false)
    public List<String> roleList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
