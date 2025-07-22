package com.companyname.one.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class AnsDto  {
	public AnsDto(int ansId, int ansNumber, int quizId, String lName, String uaName, String ansone, String anstwo,
			String ansthree, int correct,String qName) {
		// TODO Auto-generated constructor stub
		this.ansId = ansId;
		this.ans = ansNumber;
		this.quiz = new QuizDto(quizId,qName,ansone,anstwo,ansthree,correct);
		this.userName = uaName;
		this.languageName = lName;
	}
	private int ansId;
	private QuizDto quiz;
	private ExamansDto exam;
	private String languageName;
	private String userName;
	private int ans;
	private int correct;
	private int totalAns;
	
}