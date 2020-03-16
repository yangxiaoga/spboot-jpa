package com.ethan.spboot.jpa.vo.onetoone.unionpk;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Administrator
 *
 */
@Entity
public class B{
	
	private String id;
	private A a;
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	
	@OneToOne
	@JoinColumns(value={
	        @JoinColumn(name="pk_a_id", referencedColumnName="id"),
	        @JoinColumn(name="pk_a_name", referencedColumnName="name")
	})
	public A getA() {
		return a;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setA(A a) {
		this.a = a;
	}
}