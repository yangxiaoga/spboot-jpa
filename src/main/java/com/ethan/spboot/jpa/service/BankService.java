package com.ethan.spboot.jpa.service;

import com.ethan.spboot.jpa.vo.onetomuti.doubledir.Person;

/**
 * @author Administrator
 *
 */
public interface BankService {
	Person find(int id);
	Person save(Person p);
	void remove(int id);
}
