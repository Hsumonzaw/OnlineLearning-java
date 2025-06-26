package com.companyname.one.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "useraccount")
public class Languages  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int languagesId;

	private String name;

	private int amount;

	private int coursesId;

	private int studentId;

	public int getLanguagesId() {
		return languagesId;
	}

	public void setLanguagesId(int languagesId) {
		this.languagesId = languagesId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCoursesId() {
		return coursesId;
	}

	public void setCoursesId(int coursesId) {
		this.coursesId = coursesId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
