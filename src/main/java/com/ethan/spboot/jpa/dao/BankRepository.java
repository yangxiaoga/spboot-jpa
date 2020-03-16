package com.ethan.spboot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ethan.spboot.jpa.vo.onetomuti.doubledir.Person;

/**
 * @author Administrator
 *
 */
public interface BankRepository extends JpaRepository<Person, Integer>{
	
}
