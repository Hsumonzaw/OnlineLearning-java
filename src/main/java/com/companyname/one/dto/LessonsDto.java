package com.companyname.one.dto;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.companyname.one.domain.Lessons;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@AllArgsConstructor
public class LessonsDto {
	public LessonsDto(Lessons less) {
		// TODO Auto-generated constructor stub
		this.lessonsId = less.getLanguagesId();
		this.youtube = less.getYoutube();
	}
	public LessonsDto() {
		// TODO Auto-generated constructor stub
	}
	public LessonsDto(int lessonsId, String userName, String lanName, String youtube, String pdf, int amount) {
		// TODO Auto-generated constructor stub
		this.lessonsId = lessonsId;
		this.userAccount = new UserAccountDto(userName);
		this.languages = new LanguagesDto(lanName,amount);
		this.youtube = youtube;
		this.pdf = pdf;
	}
	private int lessonsId;

	private UserAccountDto userAccount;

	private LanguagesDto languages;

	private String youtube;

	private String pdf;

	private Date date;
	private Date modifiedDate;
	
}
