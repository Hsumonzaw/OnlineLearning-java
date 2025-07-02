package com.companyname.one.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.companyname.one.dto.LessonsDto;

@Entity
@Table(name = "lessons")
public class Lessons {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lessonsId;

	private int userAccountId;

	private int languagesId;

	private String youtube;

	private String pdf;

	private Date date;
	private Date modifiedDate;
	private String freeVideo;
	
	public Lessons() {
		super();
	}
	
	public Lessons(LessonsDto dto) {
		// TODO Auto-generated constructor stub
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
	public int getLanguagesId() {
		return languagesId;
	}
	public void setLanguagesId(int languagesId) {
		this.languagesId = languagesId;
	}
	public String getYoutube() {
		return youtube;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getFreeVideo() {
		return freeVideo;
	}
	public void setFreeVideo(String freeVideo) {
		this.freeVideo = freeVideo;
	}

}
