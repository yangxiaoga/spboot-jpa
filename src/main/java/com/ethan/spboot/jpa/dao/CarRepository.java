package com.ethan.spboot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ethan.spboot.jpa.vo.onetomuti.singledir.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	
	@SuppressWarnings("unchecked")
	Car save(Car car);
}
