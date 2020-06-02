package com.panwy.itask.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.panwy.itask.entity.BaseEntity;

import lombok.Data;

@Entity
@Data
public class User extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3253512231321L;
 
	@Column(nullable = false,length = 32)
	private String name; // 姓名

	@Column(length = 32)
	private String pass;

}