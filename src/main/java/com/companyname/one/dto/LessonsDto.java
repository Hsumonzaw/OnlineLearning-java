package com.companyname.one.dto;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Lessons;
import com.companyname.one.util.DateFormatDeserializer;
import com.companyname.one.util.DateFormatSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
		this.lessonsId = less.getLessonsId();
		this.youtube = less.getYoutube();
		
	}

	public LessonsDto(int lessonsId, String userName, int languageId, String lanName, String youtube, String pdf, Date date,Date modifiedDate, int amount, String ffreeVideo) {

		// TODO Auto-generated constructor stub
		this.lessonsId = lessonsId;
		this.userAccount = new UserAccountDto(userName);//for save and update error
		//this.languagesDto = new LanguagesDto(lanName,amount);
		this.youtube = youtube;
		this.pdf = pdf;
		this.date = date;
		this.modifiedDate = modifiedDate;
		this.freeVideo = ffreeVideo;
		this.languagesDto = new LanguagesDto(languageId,lanName);
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
	private String freeVideo;
	@JsonSerialize(using = DateFormatSerializer.class)
	@JsonDeserialize(using = DateFormatDeserializer.class)
	private Date date;
	@JsonSerialize(using = DateFormatSerializer.class)
	@JsonDeserialize(using = DateFormatDeserializer.class)
	private Date modifiedDate;
	
	
	
	
}
