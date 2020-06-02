package com.panwy.itask.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class BaseEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主键ID

    /**
     * 创建人
     */
    @Column(nullable = false,length = 32)
    private String crePerson;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date creTime;


    /**
     * @return String return the crePerson
     */
    public String getCrePerson() {
        return crePerson;
    }

    /**
     * @param crePerson the crePerson to set
     */
    public void setCrePerson(String crePerson) {
        this.crePerson = crePerson;
    }

    /**
     * @return Date return the creTime
     */
    public Date getCreTime() {
        return creTime;
    }

    /**
     * @param creTime the creTime to set
     */
    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void initCreInfo(String crePerson){
        
        if(this.id == null){
            this.creTime = new Date();
            this.crePerson = crePerson;
        }
       
    }

}