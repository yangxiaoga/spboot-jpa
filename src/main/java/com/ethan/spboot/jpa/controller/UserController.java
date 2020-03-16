package com.ethan.spboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ethan.spboot.jpa.service.UserService;
import com.ethan.spboot.jpa.vo.mutitoone.singledir.Group;
import com.ethan.spboot.jpa.vo.mutitoone.singledir.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/cascade")
	public void executeCascadeSave() {
		Group group1 = new Group();
		group1.setName("1组");
		User user = new User();
		user.setName("智慧");
		user.setGroup(group1);
		//多对一,由hibernate实现级联保存group
		service.save(user);
	}
	
	@RequestMapping("/find")
	public User executeFind() {
		int id = 24;
		
		/**
		 * 由于以下代码设置了级联
		 * @ManyToOne(cascade={CascadeType.ALL})//级联，增删改查都会级联
		 * @JoinColumn(name="groupId")//自身外键的名称
		 * 		public Group getGroup() {
		 * 		return group;
		 * } 
		 * 取user的同时也会相应的group也会一起取出
		 * 
		 * @ManyToOne默认是级联的，都会把多对一的一那一方取出
		 */
		return service.getUser(id);
	}
}
