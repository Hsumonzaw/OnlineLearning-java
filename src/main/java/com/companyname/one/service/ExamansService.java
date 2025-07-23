package com.companyname.one.service;

import java.util.List;

import com.companyname.one.dto.AnsDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.QuizDto;


public interface ExamansService {

//	List<ExamansDto> getExamans(int examId);

	//List<ExamansDto> getExamansbyId(int examId);

	//List<ExamansDto> getExamansbyId(int examId);

	List<ExamansDto> getExamans();

	ExamansDto addExamans(ExamansDto ex);

	ExamansDto updateExamans(ExamansDto dto);

	int deleteExamans(int examansId);

	List<QuizDto> getQuiz();

	int addQuiz(QuizDto dto);

	int updateQuiz(QuizDto dto);

	int deleteQuiz(int quizId);

	List<AnsDto> getAns();

	List<QuizDto> getQuizStudent(int languagesId);

	int saveAns(int coursesId,List<QuizDto> dtoList,int minutesCount);

	int getExamMark(int languagesId);

}
