package com.ethan.spboot.jpa.vo.onetomuti.singledir;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 一对多单向关联
 * 模拟汽车
 *
 */
@Entity
@Table(name="t_car")
public class Car {
	private int id;
	private String name;
	private Set<Wheel> wheels = new HashSet<Wheel>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="carId")//关联的外键名称，如果不写JoinColumn,默认会生成中间关系表
	public Set<Wheel> getWheels() {
		return wheels;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWheels(Set<Wheel> wheels) {
		this.wheels = wheels;
	}
}
