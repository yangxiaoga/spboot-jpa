package com.ethan.spboot.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ethan.spboot.jpa.dao.StudentRepository;
import com.ethan.spboot.jpa.service.StudentService;
import com.ethan.spboot.jpa.vo.onetoone.doubledir.Student1;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public Student1 save(Student1 student) {
		return studentRepo.save(student);
	}
}
