package com.ethan.spboot.jpa.vo.mutitoone.singledir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 组内人员 
 * 人员与组构成多对一的关系
 * 	@ManyToOne,多对一
 *
 */
@Entity
@Table(name="t_user")
public class User {
	private int id;
	private String name;
	private Group group;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@ManyToOne
	@JoinColumn(name="groupId")//自身外键的名称
	public Group getGroup() {
		return group;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
