package com.companyname.one.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "examans")
public class Examans{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private int examId;
	//private String name;
	private int userAccountId;
	//private int languagesId;
	private Integer coursesId;
	private String pdf;
	private int examMark;
	private Date date;
	private String status;
	private int minutesCount;

	
	public Examans() {
		super();
	}



	public int getExamId() {
		return examId;
	}



	public void setExamId(int examId) {
		this.examId = examId;
	}



	public int getUserAccountId() {
		return userAccountId;
	}



	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}



	public Integer getCoursesId() {
		return coursesId;
	}



	public void setCoursesId(Integer coursesId) {
		this.coursesId = coursesId;
	}



	public String getPdf() {
		return pdf;
	}



	public void setPdf(String pdf) {
		this.pdf = pdf;
	}



	public int getExamMark() {
		return examMark;
	}



	public void setExamMark(int examMark) {
		this.examMark = examMark;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getMinutesCount() {
		return minutesCount;
	}



	public void setMinutesCount(int minutesCount) {
		this.minutesCount = minutesCount;
	}
	

}
