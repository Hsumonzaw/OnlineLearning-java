package com.companyname.one.dto;

import java.util.Date;

import com.companyname.one.util.DateTimeFormatDeserializer;
import com.companyname.one.util.DateTimeFormatSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {
	public QuizDto(int quizId, int languagesId, String lName, String uaName, String qName, Date date,
			Date modifiedDate, String ansone, String anstwo, String ansthree, int correct) {
		// TODO Auto-generated constructor stub
		this.quizId = quizId;
		this.languages = new LanguagesDto(languagesId,lName);
		this.userAccount = new UserAccountDto(uaName);
		this.date = date;
		this.modifiedDate = modifiedDate;
		this.ansone = ansone;
		this.ansthree = ansthree;
		this.anstwo = anstwo;
		this.correct = correct;
	}
	public QuizDto(int quizId, String qName, String ansone, String anstwo, String ansthree, int correct) {
		// TODO Auto-generated constructor stub
		this.quizId = quizId;
		this.name = qName;
		this.ansone = ansone;
		this.ansthree = ansthree;
		this.anstwo = anstwo;
		this.correct = correct;
	}
	private int quizId;
	private LanguagesDto languages;
	private UserAccountDto userAccount;
	private String name;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date date;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date modifiedDate;
	private String ansone;
	private String anstwo;
	private String ansthree;
	private int correct;
	
	
}