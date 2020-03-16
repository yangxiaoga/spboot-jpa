package com.ethan.spboot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ethan.spboot.jpa.dao.BankRepository;
import com.ethan.spboot.jpa.service.BankService;
import com.ethan.spboot.jpa.vo.onetomuti.doubledir.Person;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	private BankRepository bankRepo;
	
	@Override
	public Person find(int id) {
		return bankRepo.getOne(id);
	}

	@Override
	public Person save(Person p) {
		return bankRepo.save(p);
	}

	@Override
	public void remove(int id) {
		bankRepo.deleteById(id);
	}

}
