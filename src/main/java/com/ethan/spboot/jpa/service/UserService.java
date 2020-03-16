package com.ethan.spboot.jpa.service;

import com.ethan.spboot.jpa.vo.mutitoone.singledir.User;

public interface UserService {
	public User save(User user);
	public User getUser(int id);
}
