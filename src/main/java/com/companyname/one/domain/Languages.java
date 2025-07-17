package com.companyname.one.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.companyname.one.dto.LanguagesDto;

@Entity
@Table(name = "languages")
public class Languages  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int languagesId;
	

	private int userAccountId;

	private String name;

	private int amount;

	private String examLink;

	private int examFee;

	
	public Languages() {
		super();
	}


	public Languages(LanguagesDto dto) {
		// TODO Auto-generated constructor stub
		this.languagesId = dto.getLanguagesId();
		this.name = dto.getName();
		this.amount = dto.getAmount();
		this.examLink = dto.getExamLink();
		this.examFee = dto.getExamFee();
	}


	public int getLanguagesId() {
		return languagesId;
	}


	public String getName() {
		return name;
	}


	public int getAmount() {
		return amount;
	}


	public String getExamLink() {
		return examLink;
	}


	public int getExamFee() {
		return examFee;
	}


	public void setLanguagesId(int languagesId) {
		this.languagesId = languagesId;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public void setExamLink(String examLink) {
		this.examLink = examLink;
	}


	public void setExamFee(int examFee) {
		this.examFee = examFee;
	}


	public int getUserAccountId() {
		return userAccountId;
	}


	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}
	
	
}
