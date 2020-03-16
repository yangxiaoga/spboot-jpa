package com.ethan.spboot.jpa.vo.onetomuti.singledir;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 轮子
 * 车与轮子构成一对多的关系
 *
 */
@Entity
@Table(name="t_wheel")
public class Wheel {
	
	private int id;

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
