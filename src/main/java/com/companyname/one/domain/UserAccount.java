package com.companyname.one.domain;
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

import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.util.Cryption;
import com.companyname.one.util.User;
@Entity
@Table(name = "useraccount")
public class UserAccount implements UserDetails  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userAccountId;
	private Integer createId;
	private Integer teacherId;
	private String name;
	private int age;
	private String photo;
	private int status;
	private String userType;
	private String userName;
	private String password;
	private String encryptPassword;
	private Date date;
	private Date modifiedDate;
	private Date startDate;
	private String address;
	private String nrc;
	private String email;
	private String phonenum;
	private String degree;
	private String file;



	public UserAccount() {
		super();
	}

	public UserAccount(UserAccountDto dto) {
		// TODO Auto-generated constructor stub
		this.userAccountId = dto.getUserAccountId();
		this.teacherId = dto.getTeacherId();
		this.name = dto.getName();
		this.age = dto.getAge();
		this.photo = dto.getPhoto();
		this.status = 1;//dto.getStatus();
		this.userType = dto.getUserType();
		if(this.userAccountId==0) {
			this.date = new Date();
		}else {
			this.date = dto.getDate();
		}
		
		this.address = dto.getAddress();
		this.nrc = dto.getNrc();
		this.email = dto.getEmail();
		this.phonenum = dto.getPhonenum();
		this.degree = dto.getDegree();
		this.file = dto.getFile();
		this.startDate = dto.getStartDate();
		this.modifiedDate = new Date(); 
		this.userName = dto.getUserName();
		this.encryptPassword = Cryption.encryption(dto.getPassword());
		this.createId = 1;//User.getUserId();
	}

	public UserAccount(int userAccountId) {
		// TODO Auto-generated constructor stub
		this.userAccountId = userAccountId;
	}

	public UserAccount(UserAccount ua) {
		// TODO Auto-generated constructor stub
		this.userAccountId = ua.getUserAccountId();
		this.name = ua.getName();
		this.age = ua.getAge();
		this.address = ua.getAddress();
		this.degree = ua.getDegree();
		this.email = ua.getEmail();
		this.date = ua.getDate();
		this.startDate = ua.getStartDate();
		this.photo = ua.getPhoto();
		this.phonenum = ua.getPhonenum();
		this.nrc = ua.getNrc();
		this.userType = ua.getUserType();
	}

	public int getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}



	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority granted = new SimpleGrantedAuthority("ROLE_"+getUserType());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(granted);
		return authorities;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public  boolean isEnabled() {
			if(this.getStatus()==0) {
				return false;
			}else {
				return true;
			}
	}
	@Transient
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUserName(); 
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
}
