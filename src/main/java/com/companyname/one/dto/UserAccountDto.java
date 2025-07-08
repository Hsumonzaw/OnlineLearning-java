package com.companyname.one.dto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.companyname.one.domain.UserAccount;
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
public class UserAccountDto{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userAccountId;
	private Integer createId;
	private String name;
	private int age;
	private String photo;
	private int status;
	private String userType;
	private String userName;
	private String password;
	private String encryptPassword;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date date;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date modifiedDate;
	@JsonSerialize(using = DateFormatSerializer.class)
	@JsonDeserialize(using = DateFormatDeserializer.class)
	private Date startDate;
	private String address;
	private String nrc;
	private String email;
	private String phonenum;
	private String degree;
	private String file;



	public UserAccountDto(UserAccount user) {
		// TODO Auto-generated constructor stub
		this.userAccountId = user.getUserAccountId();
		this.name = user.getName();
		this.age = user.getAge();
		this.photo = user.getPhoto();
		this.status = user.getStatus();
		this.userType = user.getUserType();
		this.date = user.getDate();
		this.address = user.getAddress();
		this.nrc = user.getNrc();
		this.email = user.getEmail();
		this.phonenum = user.getPhonenum();
		this.degree = user.getDegree();
		this.file = user.getFile();
		this.userName = user.getUserName();
		this.startDate = user.getStartDate();
	}



	public UserAccountDto(String userName) {
		// TODO Auto-generated constructor stub
		this.name = userName;
	}

	public UserAccountDto(int userAccountId, String userAccountName) {
		// TODO Auto-generated constructor stub
		this.userAccountId = userAccountId;
		this.userName = userAccountName;
	}
}
