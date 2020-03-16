package com.ethan.spboot.jpa.vo.onetomuti.doubledir;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 一对多双向关联
 *
 */
@Entity
@Table(name="t_person")
public class Person {
	
	private int id;
	private String name;
	private Set<BankCard> cards = new HashSet<BankCard>();
	
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
	@OneToMany(mappedBy="person")
	public Set<BankCard> getCards() {
		return cards;
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

}
