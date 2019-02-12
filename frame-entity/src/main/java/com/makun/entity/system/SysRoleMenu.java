package com.makun.entity.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @说明：[角色对应权限的实体类]
 * @author：makun
 */
@TableName("tab_sys_role_menu")
public class SysRoleMenu extends Model<SysRoleMenu> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 主键id
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    // 角色ID
    @TableField("role_id")
    private String roleId;

    // 菜单ID
    @TableField("menu_id")
    private String menuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "sysRoleMenu [id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
