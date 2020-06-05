package com.panwy.itask.entity.task;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;


import com.panwy.itask.entity.BaseEntity;

import lombok.Data;

    /**
     * 简单任务
     */
@Entity
@Data
public class SimpleTask extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 任务名称
     */
    @Column(nullable = false,length = 32)
    private String taskName;
    
    /**
     * 备注
     */
    @Column(nullable = false)
    private String remark;


    /**
     * 标签
     */
    @Column(nullable = false)
    private String tag;

    /**
     * 总结
     */
    @Column(nullable = true)
    private String summary;


    /** 
     * 状态: 未启动 进行中 已完成 已终止
     * 
     */
    @Column(nullable = false)
    private String taskState;

    /**
     * 类型
     */
    @Column(nullable = false)
    private String taskType;

    /**
     * 预估消耗时间(分钟)
     */
    private Integer expectConsumeTime;

    /**
     * 历时消耗时间(分钟)
     */
    private Integer historyTime;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 最后操作时间
     */
    private Date lastOpTime;

    /**
     * 自动填充数据避免空指针
     */
    public void fill(){
        this.summary = Optional.ofNullable(this.summary).orElse("");
        this.remark = Optional.ofNullable(this.remark).orElse("");
        this.taskType = Optional.ofNullable(this.taskType).orElse("");
        this.tag = Optional.ofNullable(this.tag).orElse("");
        this.expectConsumeTime = Optional.ofNullable(this.expectConsumeTime).orElse(0);
        this.priority = Optional.ofNullable(this.priority).orElse(0);
    }


}