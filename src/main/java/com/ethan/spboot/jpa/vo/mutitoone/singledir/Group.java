package com.ethan.spboot.jpa.vo.mutitoone.singledir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人员与组多对一
 * 组
 *
 */
@Entity
@Table(name="t_group")
public class Group {
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
