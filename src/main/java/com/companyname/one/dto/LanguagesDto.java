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
//import com.companyname.one.domain.UserAccount;
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

	public LanguagesDto(LanguagesDto dto) {
		// TODO Auto-generated constructor stub
		this.languagesId = dto.getLanguagesId();
		this.name = dto.getName();
		this.amount = dto.getAmount();
		this.examLink = dto.getExamLink();
		this.examFee = dto.getExamFee();
//		 if (dto.getUserAccount() != null) {
//		        UserAccountDto user = new UserAccountDto(); 
//		        user.setUserAccountId(dto.getUserAccount().getUserAccountId());
//		        this.userAccount = user;
//		    }
	}
	public LanguagesDto(int languagesId, String name, int amount, String examLink,int examFee) {
		// TODO Auto-generated constructor stub
		this.languagesId = languagesId;

		this.name = name;
		this.amount = amount;
		this.examLink =examLink;
		this.examFee = examFee;		
		//this.userAccount = new UserAccountDto(userAccountId);//for save and update error
		
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



//	public LanguagesDto(int languagesId, String name, int amount, String examLink, int examFee) {
//		// TODO Auto-generated constructor stub
//		this.languagesId = languagesId;
//		this.name = name;
//		this.amount = amount;
//		this.examLink =examLink;
//		this.examFee = examFee;
//	}



	private int languagesId;
	
	private LessonsDto lessonsDto;
	
	private UserAccountDto userAccount; 

	private String name;

	private int amount;

	private String examLink;

	private int examFee;

	
}
