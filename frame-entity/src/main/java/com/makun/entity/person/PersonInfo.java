package com.makun.entity.person;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @说明:个人资料实体类
 * @author makun
 */
@TableName("tab_per_myinfo")
public class PersonInfo extends Model<PersonInfo> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 个人信息id
    @TableId(value = "id", type = IdType.UUID)
    private String personId;

    // 昵称
    @TableField("person_name")
    private String personName;

    // 头像
    @TableField("person_header")
    private String personHeader;

    // 个性签名
    @TableField("person_sign")
    private String personSign;

    // 性别
    @TableField("person_sex")
    private String personSex;

    // 地址
    @TableField("person_address")
    private String personAddress;

    // 手机号码
    @TableField("person_tel")
    private String personTel;

    // 电子邮箱
    @TableField("person_email")
    private String personEmail;

    // 个人编号
    @TableField("person_num")
    private String personNum;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonHeader() {
        return personHeader;
    }

    public void setPersonHeader(String personHeader) {
        this.personHeader = personHeader;
    }

    public String getPersonSign() {
        return personSign;
    }

    public void setPersonSign(String personSign) {
        this.personSign = personSign;
    }

    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonNum() {
        return personNum;
    }

    public void setPersonNum(String personNum) {
        this.personNum = personNum;
    }

    @Override
    public String toString() {
        return "personInfo [personId=" + personId + ", personName=" + personName + ", personHeader=" + personHeader
                + ", personSign=" + personSign + ", personSex=" + personSex + ", personAddress=" + personAddress
                + ", personTel=" + personTel + ", personEmail=" + personEmail + ", personNum=" + personNum + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.personId;
    }

}
