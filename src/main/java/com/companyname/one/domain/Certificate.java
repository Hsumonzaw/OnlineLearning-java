package com.companyname.one.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "certificate")
public class Certificate{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int certificateId;
	private int userAccountId;
	private int languagesId;
	private int examId;
	public int getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}
	public int getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}
	public int getLanguagesId() {
		return languagesId;
	}
	public void setLanguagesId(int languagesId) {
		this.languagesId = languagesId;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	
}
