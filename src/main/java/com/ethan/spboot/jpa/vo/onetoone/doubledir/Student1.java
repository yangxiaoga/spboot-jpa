package com.ethan.spboot.jpa.vo.onetoone.doubledir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 模拟学生
 * 一对一双向关联 【@OneToOne】
 * 
 * Hibernate: 
 * create table student (
 * id varchar(255) not null,
 *  name varchar(255), 
 *  fk_card varchar(255), 
 *  primary key (id)) engine=MyISAM
 *  
 * create table student_card (
 * id varchar(255) not null, 
 * name varchar(255), 
 * primary key (id)) engine=MyISAM
 * 
 * alter table student 
 * add constraint FKmnf5ebku1ib42a5nvs5232m9y 
 * foreign key (fk_card) references student_card (id)
 * 
 */
@Entity
@Table
public class Student1 {
	private String id;
	private StudentCard studentCard;
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
	
	@OneToOne
	@JoinColumn(name="fk_card") //指定建表时，外键的名称
	public StudentCard getStudentCard() {
		return studentCard;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setStudentCard(StudentCard studentCard) {
		this.studentCard = studentCard;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}


