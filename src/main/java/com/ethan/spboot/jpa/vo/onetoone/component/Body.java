package com.ethan.spboot.jpa.vo.onetoone.component;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 组件映射
 * 模拟身体
 * @Embedded //标记需要嵌入的组件
 */
@Entity
@Table(name="t_body")
public class Body {
	private String id;
	private Brain brain;
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	
	@Embedded //嵌入
	public Brain getBrain() {
		return brain;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setBrain(Brain brain) {
		this.brain = brain;
	}
}
