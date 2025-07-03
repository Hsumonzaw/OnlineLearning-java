package com.companyname.one.dto;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Lessons;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class LessonsDto {
	public LessonsDto(Lessons less) {
		// TODO Auto-generated constructor stub
		this.lessonsId = less.getLanguagesId();
		this.youtube = less.getYoutube();
		
	}

	public LessonsDto(int lessonsId, String userName, String lanName, String youtube, String pdf, int amount) {
		// TODO Auto-generated constructor stub
		this.lessonsId = lessonsId;
		this.userAccount = new UserAccountDto(userName);
		//this.languagesDto = new LanguagesDto(lanName,amount);
		this.youtube = youtube;
		this.pdf = pdf;

//		this.date = new Date();
//		this.modifiedDate = new Date();
	}
	public LessonsDto(int lessonsId) {
		// TODO Auto-generated constructor stub
		this.lessonsId = lessonsId;
		
	}
	private int lessonsId;

	private UserAccountDto userAccount;
	
	private LanguagesDto languagesDto;

	private String youtube;

	private String pdf;

	private Date date;
	private Date modifiedDate;
	
	
	
	
}
