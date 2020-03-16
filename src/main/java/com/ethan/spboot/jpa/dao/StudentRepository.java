package com.ethan.spboot.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ethan.spboot.jpa.vo.onetoone.doubledir.Student1;

public interface StudentRepository extends JpaRepository<Student1, String>{
	
	@SuppressWarnings("unchecked")
	Student1 save(Student1 student);
}
