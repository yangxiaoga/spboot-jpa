package com.ethan.spboot.jpa.vo.onetomuti.doubledir;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 一对多双向关联
 */
@Entity
@Table(name="t_person")
public class Person {
	
	private int id;
	private String name;
	private Set<BankCard> cards = new HashSet<BankCard>();
	private Map<Integer,BankCard> entry = new HashMap<Integer,BankCard>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	//凡是双向需要加mappedBy，避免生成两个重复外键
	@OneToMany(mappedBy="person",cascade={CascadeType.ALL})
	public Set<BankCard> getCards() {
		return cards;
	}

	//Map映射
	@OneToMany(mappedBy="person",cascade={CascadeType.ALL})
	@MapKey(name="id")//一般选择实体的主键
	public Map<Integer, BankCard> getEntry() {
		return entry;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setCards(Set<BankCard> cards) {
		this.cards = cards;
	}

	public void setEntry(Map<Integer, BankCard> entry) {
		this.entry = entry;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", cards=" + cards + "]";
	}
}
