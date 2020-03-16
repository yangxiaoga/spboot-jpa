package com.ethan.spboot.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ethan.spboot.jpa.service.CarService;
import com.ethan.spboot.jpa.vo.onetomuti.singledir.Car;
import com.ethan.spboot.jpa.vo.onetomuti.singledir.Wheel;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	CarService service;
	
	@RequestMapping("/cascade")
	public void executeCascadeSave() {
		//一对多,由hibernate实现级联保存wheel
		Wheel wheel = new Wheel();
		Wheel whee2 = new Wheel();
		Car car = new Car();
		car.setName("Audi");
		car.getWheels().add(whee2);
		car.getWheels().add(wheel);
		service.save(car);
	}
}
