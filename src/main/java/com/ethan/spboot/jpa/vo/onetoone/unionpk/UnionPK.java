package com.ethan.spboot.jpa.vo.onetoone.unionpk;

import java.io.Serializable;

/**
 * 联合主键类(id,name)，对应的类A(id,name,age);B->A
 * 联合主键类需要实现Serializable接口 - 可能会涉及到序列化存储
 * 需要重写equals方法和hashCode方法
 *
 */

public class UnionPK implements Serializable{
	
	private static final long serialVersionUID = 8874686468521454489L;
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int hashCode() { 
		return this.getName().hashCode();
	}
	public boolean equals(Object o) {
		if (this==o){
			return true;
		}
		if (o instanceof UnionPK) {
			UnionPK wpk = (UnionPK)o;
			if (this.getId().equals(wpk.getId())&&this.getName().equals(wpk.getName())){
				return true;
			}
		}
		return false;
	}
}




