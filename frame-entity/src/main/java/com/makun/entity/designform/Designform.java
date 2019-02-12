//package com.makun.entity.designform;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import com.baomidou.mybatisplus.activerecord.Model;
//import com.baomidou.mybatisplus.annotations.TableField;
//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;
//import com.baomidou.mybatisplus.enums.IdType;
//
///**
// * @说明：表单实体类
// * @author makun
// */
//@TableName("tab_workflow_designform")
//public class Designform extends Model<Designform> {
//
//    @TableField(exist = false)
//    private static final long serialVersionUID = 1L;
//
//    @TableId(value = "id", type = IdType.UUID)
//    private String id;
//
//    @TableField("form_name")
//    private String formName;// 表单名称
//
//    @TableId(value = "display_name")
//    private String displayName;// 显示的名称
//
//    @TableField("ora_html")
//    private String oraHtml;// 表单html字符串
//
//    @TableField("parse_html")
//    private String parseHtml;// 解析的html
//
//    @TableField("fields")
//    private Integer fields;// 字段个数（input,button,radio）
//
//    @TableField("form_category")
//    private String formCategory;// 表单类型
//
//    @TableField("form_js")
//    private String formJs;// form需要的js
//
//    @TableField("form_css")
//    private String formCss;// form需要的css
//
//    @TableField("create_id")
//    private String createId;// 创建人
//
//    @TableField("create_time")
//    private Date createTime;// 数据创建时间
//
//    @TableField(exist = false)
//    private String createName;// 创建人姓名
//
//    @TableField("update_id")
//    private String updateId;// 修改人
//
//    @TableField("update_time")
//    private Date updateTime;// 数据修改时间
//
//    @TableField(exist = false)
//    private String updateName;// 修改人姓名
//
//    @TableField("JSONDATA")
//    private String jsondata;// 解析出的json数据
//
//    @TableField(exist = false)
//    private String typename;// 分类名称
//
//    @Override
//    protected Serializable pkVal() {
//        return this.id;
//    }
//
//}
