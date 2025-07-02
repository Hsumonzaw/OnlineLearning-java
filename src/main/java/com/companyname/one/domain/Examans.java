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
	private int coursesId;
	private String pdf;
	private int examMark;
	private Date date;
	private String status;
	

	
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
	
	public int getCoursesId() {
		return coursesId;
	}
	public void setCoursesId(int coursesId) {
		this.coursesId = coursesId;
	}
	//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getLanguagesId() {
//		return languagesId;
//	}
//	public void setLanguagesId(int languagesId) {
//		this.languagesId = languagesId;
//	}
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
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((examid == null) ? 0 : examid.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		Examans other = (Examans) obj;
//		if (examid == null) {
//			if (other.examid != null) {
//				return false;
//			}
//		} else if (!examid.equals(other.examid)) {
//			return false;
//		}
//		return true;
//	}

}
