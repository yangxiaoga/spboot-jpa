package com.ethan.spboot.jpa.vo.onetoone.doubledir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 模拟学生的学生卡
 * 一对一双向@OneToOne(mappedBy="studentCard"),由Student主导
 */
@Entity
@Table(name="t_student_card")
public class StudentCard {
	private String id;
	private Student1 student;
	private String name;
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	@OneToOne(mappedBy="studentCard")//由Student主导
	public Student1 getStudent() {
		return student;
	}
	
	public void setStudent(Student1 student) {
		this.student = student;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}