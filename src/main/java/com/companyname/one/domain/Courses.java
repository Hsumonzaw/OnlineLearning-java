
package com.companyname.one.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "courses")
public class Courses  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int coursesId;

	private int userAccountId;
	
	private int studentId;

	private int languagesId;

	private String type;

	private int amount;
	
	private String cphoto;

	private Date receivedDate;
	
	private Date date;
	
	private Date modifiedDate;
	
	public Courses() {
		super();
	}

	public int getCoursesId() {
		return coursesId;
	}

	public int getUserAccountId() {
		return userAccountId;
	}

	public int getStudentId() {
		return studentId;
	}

	public int getLanguagesId() {
		return languagesId;
	}

	public String getType() {
		return type;
	}

	public int getAmount() {
		return amount;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public Date getDate() {
		return date;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setCoursesId(int coursesId) {
		this.coursesId = coursesId;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setLanguagesId(int languagesId) {
		this.languagesId = languagesId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCphoto() {
		return cphoto;
	}

	public void setCphoto(String cphoto) {
		this.cphoto = cphoto;
	}


	
	
	
	
}



