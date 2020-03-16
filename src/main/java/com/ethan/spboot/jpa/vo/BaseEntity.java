package com.ethan.spboot.jpa.vo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Administrator
 * 基类
 * @MappedSuperclass标识基类，这个基类不会以一个实体记录的形式映射到数据库中，
 * 但继承它的子类在映射数据库的时候会自动扫描该基类实体的映射属性，不论是自动建表、
 * 添加记录、查询等操作，都可以虽子类中的属性一同映射到数据库中
 */

@MappedSuperclass
public class BaseEntity  implements Serializable{
	
	private static final long serialVersionUID = 4158913702554191572L;
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	protected String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
