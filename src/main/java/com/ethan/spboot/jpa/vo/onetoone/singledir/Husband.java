package com.ethan.spboot.jpa.vo.onetoone.singledir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * 一对一单向关联
 * Husband - Wife
 * Hibernate: 
 * create table husband (
 * 		id varchar(255) not null, 
 * 		name varchar(255), 
 * 		wife_id varchar(255), 
 * 		primary key (id)) engine=MyISAM
 * 
 * create table wife (
 * 		id varchar(255) not null, 
 * 		name varchar(255), 
 * 		primary key (id)) engine=MyISAM
 * 
 *  alter table husband 
 * 	add constraint FKhn2hu2061l6i0xqnra2p8ywei 
 *  foreign key (wife_id) references wife (id)
 *  
 *  Hibernate会在Husband中自动创建Wife外键
 *
 */
@Entity
public class Husband {
	private String id;
	private Wife wife;
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
	@JoinColumn(name="fk_wife") //指定建表时，外键的名称
	public Wife getWife() {
		return wife;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setWife(Wife wife) {
		this.wife = wife;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
