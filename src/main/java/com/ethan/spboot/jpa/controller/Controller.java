package com.ethan.spboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ethan.spboot.jpa.service.ComputerService;
import com.ethan.spboot.jpa.vo.Computer;

@RestController
public class Controller {
	
	@Autowired
	ComputerService service;
	
	@RequestMapping("/get")
	public Computer execute() {
		return service.findByName("联想");
	}
	
	@RequestMapping("/save")
	public Computer executeSave() {
		Computer c = new Computer();
		c.setName("新品牌");
		c.setPrice(5690.34);
		return service.save(c);
	}
}
