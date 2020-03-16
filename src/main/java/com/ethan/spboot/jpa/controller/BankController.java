package com.ethan.spboot.jpa.controller;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ethan.spboot.jpa.service.BankService;
import com.ethan.spboot.jpa.vo.onetomuti.doubledir.BankCard;
import com.ethan.spboot.jpa.vo.onetomuti.doubledir.Person;

@RestController
@RequestMapping("/bank")
public class BankController {
	
	@Autowired
	BankService service;

	@RequestMapping("/save")
	public void save() {
		Person p = new Person();
		p.setName("我是谁");
		
	    BankCard card1 = new BankCard();
	    card1.setName("中国银行卡");
	    card1.setPerson(p);
	    
	    BankCard card2 = new BankCard();
	    card2.setName("农业银行卡");
	    card2.setPerson(p);
	    
	    p.getCards().add(card1);
	    p.getCards().add(card2);
	   
	    Person p1 = service.save(p);
	    System.out.println(p1);
	}
	
	
	@RequestMapping("/get")
	public void executeGet() {
		int id = 39;
		Person p1 =  service.find(id);
		
		//测试Map映射
		/**
		 * 40:中国银行卡
		 * 41:农业银行卡
		 */
		Map<Integer,BankCard> entrys = p1.getEntry();
		for(Entry<Integer,BankCard> entry: entrys.entrySet()) {
			System.out.println(entry.getKey()+":"+entry.getValue().getName());
		}
		 System.out.println(p1);
	}
	
	//删除对象
	@RequestMapping("/delete")
	public void executeRemove() {
		//一般情况需要先查询数据库有没有这个对象，再决定是否删除
		int id = 36;
		service.remove(id);
	}
}
