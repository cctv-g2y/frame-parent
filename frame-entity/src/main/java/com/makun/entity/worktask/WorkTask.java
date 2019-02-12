package com.makun.entity.worktask;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @说明：工作流程主体核心类
 * @author makun
 */
@TableName("tab_workflow_task")
public class WorkTask extends Model<WorkTask> {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    // id
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
