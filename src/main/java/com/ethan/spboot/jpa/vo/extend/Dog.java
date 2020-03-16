package com.ethan.spboot.jpa.vo.extend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 继承映射
 *
 */
@Entity
@Table(name = "t_dog")
public class Dog extends Animal{
	
	@Column(name="weight")
	private double weight;

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
}
