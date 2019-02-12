package com.makun.entity.fastdfs;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * @说明：[文档实体]
 * @author: makun
 */
@TableName("tab_fastdfs_document")
public class Document extends Model<Document> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // 主键id
    @TableId(value = "id", type = IdType.UUID)
    private String docId;

    // 文件名称
    @TableField("name")
    private String name;

    // 文件描述
    @TableField("mark")
    private String mark;

    // 存储文件节点名称
    @TableField("group_name")
    private String groupName;

    // 文件路径
    @TableField("file_path")
    private String filePath;

    // 文件类型
    @TableField("file_type")
    private String fileType;

    // 文件大小
    @TableField("file_size")
    private int fileSize;

    // 是否是下载空间内的文件 0不是，1是
    @TableField("type")
    private String type;

    // 文件分类
    @TableField("file_class")
    private String fileClass;

    // 创建时间
    @TableField("create_time")
    private Date createTime;

    // 修改时间
    @TableField("update_time")
    private Date updateTime;

    // 创建人
    @TableField("create_id")
    private String createId;

    // 修改人
    @TableField("update_id")
    private String updateId;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileClass() {
        return fileClass;
    }

    public void setFileClass(String fileClass) {
        this.fileClass = fileClass;
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
        return "Document [docId=" + docId + ", name=" + name + ", mark=" + mark + ", groupName=" + groupName
                + ", filePath=" + filePath + ", fileType=" + fileType + ", fileSize=" + fileSize + ", type=" + type
                + ", fileClass=" + fileClass + ", createTime=" + createTime + ", updateTime=" + updateTime
                + ", createId=" + createId + ", updateId=" + updateId + "]";
    }

    @Override
    protected Serializable pkVal() {
        return this.docId;
    }

}
