package com.ethan.spboot.jpa.service;

import com.ethan.spboot.jpa.vo.Computer;
import com.ethan.spboot.jpa.vo.mutitoone.singledir.User;
import com.ethan.spboot.jpa.vo.onetomuti.singledir.Car;

public interface ComputerService {
	Computer findById(long id);
	Computer save(Computer computer);
	Computer findByName(String name);
	User save(User user);
	Car save(Car car);
}
