package com.antcloud.workflow.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author beg
 * @date 2026/11/17
 */
@Data
@TableName("antcloud_task_message_deal")
public class WfTaskMessageDeal {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String taskId;
    private String instanceId;
    private String assignee;
}
