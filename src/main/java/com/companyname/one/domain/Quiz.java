package com.companyname.one.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.companyname.one.dto.QuizDto;
import com.companyname.one.util.User;

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int quizId;
	private int languagesId;
	private int userAccountId;
	private String name;
	private Date date;
	private Date modifiedDate;
	private String ansone;
	private String anstwo;
	private String ansthree;
	private int correct;
	public Quiz(QuizDto dto) {
		// TODO Auto-generated constructor stub
		this.quizId = dto.getQuizId();
		this.languagesId = dto.getLanguages().getLanguagesId();
		this.userAccountId = User.getUserId();
		this.name = dto.getName();
		this.date = new Date();
		this.modifiedDate = new Date();
		this.ansone = dto.getAnsone();;
		this.anstwo = dto.getAnstwo();
		this.ansthree = dto.getAnsthree();
		this.correct = dto.getCorrect();
	}
	public Quiz(int quizId2) {
		// TODO Auto-generated constructor stub
		this.quizId = quizId2;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public int getLanguagesId() {
		return languagesId;
	}
	public void setLanguagesId(int languagesId) {
		this.languagesId = languagesId;
	}
	public int getUserAccountId() {
		return userAccountId;
	}
	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getAnsone() {
		return ansone;
	}
	public void setAnsone(String ansone) {
		this.ansone = ansone;
	}
	public String getAnstwo() {
		return anstwo;
	}
	public void setAnstwo(String anstwo) {
		this.anstwo = anstwo;
	}
	public String getAnsthree() {
		return ansthree;
	}
	public void setAnsthree(String ansthree) {
		this.ansthree = ansthree;
	}
	public int getCorrect() {
		return correct;
	}
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	
}