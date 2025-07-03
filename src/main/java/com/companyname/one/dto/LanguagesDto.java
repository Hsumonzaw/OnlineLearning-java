package com.companyname.one.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.companyname.one.domain.Languages;
import com.companyname.one.util.DateFormatDeserializer;
import com.companyname.one.util.DateTimeFormatDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class LanguagesDto {

	public LanguagesDto(Languages language) {
		// TODO Auto-generated constructor stub
		this.languagesId = language.getLanguagesId();
		this.name = language.getName();
		this.amount = language.getAmount();
		this.examLink = language.getExamLink();
		this.examFee = language.getExamFee();
	}

	public LanguagesDto(int languagesId, String languagesName) {
		// TODO Auto-generated constructor stub
		this.languagesId = languagesId;
		this.name = languagesName;
	}

	

//	public LanguagesDto(String lanName) {
//		// TODO Auto-generated constructor stub
//		this.name = lanName;
//	}



	private int languagesId;

	private String name;

	private int amount;

	private String examLink;

	private int examFee;

	
}
