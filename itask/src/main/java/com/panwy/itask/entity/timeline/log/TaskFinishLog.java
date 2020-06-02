package com.panwy.itask.entity.timeline.log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;


import com.panwy.itask.entity.BaseEntity;

import lombok.Data;

@Entity
@Data
public class TaskFinishLog extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 任务ID
     */
    @Column(nullable = false)
    private Long TaskId;

    /**
     * 预期开始时间
     */
    @Column(nullable = false)
    private Date expectStartTime;

    /**
     * 预期结束时间
     */
    @Column(nullable = false)
    private Date expectEndTime;

    /**
     * 实际期开始时间
     */
    @Column(nullable = false)
    private Date realStartTime;
    
    /**
     * 实际结束时间
     */
    @Column(nullable = false)
    private Date realEndTime;


    /**
     * 预期消耗时间(分钟)
     */
    @Column(nullable = false)
    private int expectConsumeTime;

    /**
     * 实际消耗时间(分钟)
     */
    @Column(nullable = false)
    private int realConsumeTime;

    /**
     * 实际效率(预期耗时/实际耗时)
     */
    @Column(nullable = false)
    private BigDecimal realEfficiency;

    /**
     * 延期消耗时间(分钟)>=0
     */
    @Column(nullable = false)
    private int delayTime;   

    
    /**
     * 延期占比(延期耗时/预期耗时)
     */
    @Column(nullable = false)
    private int delayRate; 


    /**
     * 备注
     */
    @Column(nullable = false)
    private String remark;
    
}