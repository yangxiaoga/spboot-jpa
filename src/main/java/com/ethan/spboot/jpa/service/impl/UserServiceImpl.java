package com.ethan.spboot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ethan.spboot.jpa.dao.UserRepository;
import com.ethan.spboot.jpa.service.UserService;
import com.ethan.spboot.jpa.vo.mutitoone.singledir.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * 通过id查询User
	 * @param id
	 */
	@Override
	public User getUser(int id) {
		return userRepository.getOne(id);
	}

}
