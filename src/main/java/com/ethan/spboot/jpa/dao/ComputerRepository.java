package com.ethan.spboot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ethan.spboot.jpa.vo.Computer;
import com.ethan.spboot.jpa.vo.mutitoone.singledir.User;
import com.ethan.spboot.jpa.vo.onetomuti.singledir.Car;

public interface ComputerRepository extends JpaRepository<Computer, String>{
	
	Computer findById(long id);
	
	@SuppressWarnings("unchecked")
	Computer saveAndFlush(Computer computer);
	
	@Query(value = "SELECT c FROM Computer c WHERE name=:name")
	//使用SQL,@Query(value = "SELECT * FROM yyh_user WHERE name=?", nativeQuery = true)
	Computer findByName(@Param("name") String name);

	User save(User user);
	
	Car save(Car car);
}
