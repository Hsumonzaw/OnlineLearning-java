package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Examans;
import com.companyname.one.domain.Quiz;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.AnsDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.QuizDto;

public interface ExamansDao {

	//List<Object[]> getExamansbyId(int examId);

	List<ExamansDto> getExamans();

	void addExamans(Examans ex);

	void updateExamans(Examans ex);

	void deleteExamans(int examansId);

	List<QuizDto> getQuiz();

	void addQuiz(Quiz q);

	void updateQuiz(Quiz q);

	void deleteQuiz(Quiz quiz);

	List<AnsDto> getAns();

}
