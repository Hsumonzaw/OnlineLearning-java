package com.companyname.one.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentsId;
	private int lessonsId;
	private int userAccountId;
	private String message;
	private Date date;
	private Date modifieddate;
	public int getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(int commentsId) {
		this.commentsId = commentsId;
	}
	public int getLessonsId() {
		return lessonsId;
	}
	public void setLessonsId(int lessonsId) {
		this.lessonsId = lessonsId;
	}
	public int getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}


}