package com.ethan.spboot.jpa.vo.mutitomulti.singledir;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
/**
 * 	多对多单向关联
 *  供应商
 * 
 *	@ManyToMany
 *
 *	指定关系表中的字段的名称
 *  
 *	@JoinTable(
 *		name="t_provider_customer",
 *	    joinColumns=@JoinColumn(name="provider_id", 
 *	    						referencedColumnName="id"),
 *	    inverseJoinColumns=@JoinColumn(name="customer_id", 
 *	    						referencedColumnName="id")	
 *	)
 */
@Entity
@Table(name="t_provider")
public class Provider {
	private int id;
	private String name;
	private Set<Customer> customers;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	
	@ManyToMany
	@JoinTable(
		name="t_provider_customer",
	    joinColumns=@JoinColumn(name="provider_id", 
	    						referencedColumnName="id"),
	    inverseJoinColumns=@JoinColumn(name="customer_ID", 
	    						referencedColumnName="id")	
	)
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
}
