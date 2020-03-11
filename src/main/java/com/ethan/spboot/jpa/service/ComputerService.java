package com.ethan.spboot.jpa.service;

import com.ethan.spboot.jpa.vo.Computer;

/**
 * @author Administrator
 *
 */
public interface ComputerService {
	Computer findById(long id);
	Computer save(Computer computer);
	Computer findByName(String name);
	
}
