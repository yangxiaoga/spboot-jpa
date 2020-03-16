package com.ethan.spboot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ethan.spboot.jpa.dao.ComputerRepository;
import com.ethan.spboot.jpa.service.ComputerService;
import com.ethan.spboot.jpa.vo.Computer;
import com.ethan.spboot.jpa.vo.mutitoone.singledir.User;
import com.ethan.spboot.jpa.vo.onetomuti.singledir.Car;

@Service
public class ComputerServiceImpl implements ComputerService{

	@Autowired
	private ComputerRepository repo;
	
	@Override
	public Computer findById(long id) {
		return repo.findById(id);
	}

	@Override
	public Computer save(Computer computer) {
		return repo.save(computer);
	}
	
	@Override
	public Computer findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	@Transactional(timeout = 5)
	public User save(User user) {
		return repo.save(user);
	}

	@Override
	public Car save(Car car) {
		return repo.save(car);
	}

}
