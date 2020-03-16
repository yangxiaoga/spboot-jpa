package com.ethan.spboot.jpa.vo.design;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 学生类
 */
@Entity
@Table(name="t_student")
public class Student {
	
	private int id;
	private String name;
	private Set<Course> courses = new HashSet<Course>();//学生所修课程
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}
	
	//目的是方便找出每个学生所修的课程
	//学生与课程间是多对多的关系
	//中间表使用由Score类建好的t_score表
	@ManyToMany
	@JoinTable( 
			name="t_score",
			joinColumns=@JoinColumn(name="studentId"),
			inverseJoinColumns=@JoinColumn(name="courseId")
	)
	//默认生成的表会以studentId，courseId为联合主键
	//因此需要手动重新创建t_score表
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
}
