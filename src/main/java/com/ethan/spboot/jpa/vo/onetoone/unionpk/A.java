package com.ethan.spboot.jpa.vo.onetoone.unionpk;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.GenericGenerator;

//需要的注解@IdClass(value = UnionPK.class)
//属性的注解，联合主键@Id
@Entity
@IdClass(value = UnionPK.class)
public class A {
	private String id;
	private String name;
	private int age;
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	
	@Id
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@Column(name="age")
	public int getAge() {
		return age;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
