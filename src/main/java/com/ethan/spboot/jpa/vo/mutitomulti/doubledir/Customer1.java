package com.ethan.spboot.jpa.vo.mutitomulti.doubledir;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 多对多单向关联
 *客户
 */
@Entity
@Table(name="t_customer1")
public class Customer1 {
	private int id;
	private String name;
	
	private Set<Provider1> provider1s = new HashSet<Provider1>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@ManyToMany(mappedBy="customers")//配置另一个类的属性名
	public Set<Provider1> getProvider1s() {
		return provider1s;
	}

	public void setProvider1s(Set<Provider1> provider1s) {
		this.provider1s = provider1s;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
