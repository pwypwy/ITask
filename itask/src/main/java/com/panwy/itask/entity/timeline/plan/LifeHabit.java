package com.panwy.itask.entity.timeline.plan;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;


import com.panwy.itask.entity.BaseEntity;

import lombok.Data;

@Entity
@Data
public class LifeHabit extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 习惯名称
     */
    @Column(nullable = false,length = 32)
    private String habitName;

    /**
     * 预估消耗时间
     */
    private int expectConsumeTime;
    
    /**
     * 预期开始时间
     */
    @Column(nullable = false)
    private int expectStartTime;
    
    /**
     * 预期结束时间
     */
    @Column(nullable = false)
    private int expectEndTime;

    /**
     * 状态: 未开始,进行中,已暂停,已作废
     */
    @Column(nullable = false)
    private short habitState;

    /**
     * 类型
     */
    @Column(nullable = false)
    private short habitType;

    /**
     * 备注
     */
    @Column(nullable = false)
    private String remark;

    /**
     * 周期
     */
    @Column(nullable = false)
    private short cycle;
    /**
     * 标签
     */
    @Column(nullable = false)
    private String tag;

}