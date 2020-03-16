package com.ethan.spboot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ethan.spboot.jpa.dao.CarRepository;
import com.ethan.spboot.jpa.service.CarService;
import com.ethan.spboot.jpa.vo.onetomuti.singledir.Car;

@Service
public class CarServiceImpl implements CarService{

	@Autowired
	private CarRepository carRepo;
	
	@Override
	public Car save(Car car) {
		return carRepo.saveAndFlush(car);
	}

}
