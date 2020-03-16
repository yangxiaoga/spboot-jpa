package com.ethan.spboot.jpa.vo.design;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 分数类
 */
@Entity
@Table(name="t_score")
public class Score {
	
	private int id;
	private Student student;
	private Course course;
	private double score;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	//一个学生对应多个分数
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="studentId")
	public Student getStudent() {
		return student;
	}

	//一门课程对应多个分数
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="courseId")
	public Course getCourse() {
		return course;
	}
	
	@Column(name="score")
	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public void setId(int id) {
		this.id = id;
	}
}
