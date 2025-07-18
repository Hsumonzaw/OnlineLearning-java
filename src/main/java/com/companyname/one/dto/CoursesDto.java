package com.companyname.one.dto;

import java.util.Date;

import com.companyname.one.util.DateFormatDeserializer;
import com.companyname.one.util.DateFormatSerializer;
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
public class CoursesDto {
	
	public CoursesDto(int coursesId, String type, int amount, String cphoto,String examLink,String pdf, String description, Date receivedDate, Date date, Date modifiedDate) {
	    // TODO Auto-generated constructor stub
	    this.coursesId = coursesId;
	    this.type = type;
	    this.amount = amount;
	    this.cphoto = cphoto;
	    this.examLink = examLink;//new
	    this.pdf = pdf; // new
	    this.description = description;
	    this.receivedDate = receivedDate;
	    this.date = date;
	    this.modifiedDate = modifiedDate;
	    
	  }
	

	
	public CoursesDto(int languagesId) {
		// TODO Auto-generated constructor stub
		this.languagesId = languagesId;
	}



	private int coursesId;
	private int languagesId;


	private UserAccountDto userAccountDto;
	
	private UserAccountDto studentDto;

	private LanguagesDto languagesDto;

	private String type;

	private int amount;
	
	private String cphoto;
	
	private String examLink;//new
	
	private String pdf; //new
	
	private String description;
	
	@JsonSerialize(using = DateFormatSerializer.class)
	@JsonDeserialize(using = DateFormatDeserializer.class)
	private Date receivedDate;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date date;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date modifiedDate;
}
