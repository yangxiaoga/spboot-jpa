package com.ethan.spboot.jpa.vo.onetomuti.doubledir;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 一对多双向关联
 * 银行卡
 */
@Entity
@Table(name="t_bankcard")
public class BankCard {
	private int id;
	private String name;
	private Person person;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
	public Person getPerson() {
		return person;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
