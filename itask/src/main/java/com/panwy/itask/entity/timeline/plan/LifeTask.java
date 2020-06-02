package com.panwy.itask.entity.timeline.plan;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import com.panwy.itask.entity.BaseEntity;

import lombok.Data;

@Entity
@Data
public class LifeTask  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 习惯名称
     */
    @Column(nullable = false,length = 32)
    private String habitName;

    /**
     * 预估消耗时间
     */
    @Column(nullable = false)
    private int expectConsumeTime;

    @Column(nullable = false)
    private int priority;
    
    /**
    //  * 预期开始时间
    //  */
    // private Date expectStartTime;
    
    // /**
    //  * 预期结束时间
    //  */
    // private Date expectEndTime;

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
     * 标签
     */
    @Column(nullable = false)
    private String tag;
    
}