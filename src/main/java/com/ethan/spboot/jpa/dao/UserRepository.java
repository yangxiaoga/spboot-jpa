package com.ethan.spboot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ethan.spboot.jpa.vo.mutitoone.singledir.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@SuppressWarnings("unchecked")
	User save(User user);

}
