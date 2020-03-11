package com.ethan.spboot.jpa.vo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Administrator
 * @Entity标识一个实体类，任何Hibernate映射对象都要有这个注解。
 */

@Entity
@Table(name = "computer")
public class Computer extends BaseEntity{
	private static final long serialVersionUID = 4204039273653353737L;

}
