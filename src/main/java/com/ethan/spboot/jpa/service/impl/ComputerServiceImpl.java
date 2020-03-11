package com.ethan.spboot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ethan.spboot.jpa.dao.ComputerRepository;
import com.ethan.spboot.jpa.service.ComputerService;
import com.ethan.spboot.jpa.vo.Computer;

/**
 * @author Administrator
 *
 */
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

}
