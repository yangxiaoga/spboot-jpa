package com.ethan.spboot.jpa.vo.mutitomulti.singledir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 多对多单向关联
 *客户
 */
@Entity
@Table(name="t_customer")
public class Customer {
	private int id;
	private String name;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
