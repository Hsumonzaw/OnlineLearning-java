package com.companyname.one.service;

import java.util.List;


import com.companyname.one.dto.ExamansDto;


public interface ExamansService {

//	List<ExamansDto> getExamans(int examId);

	//List<ExamansDto> getExamansbyId(int examId);

	//List<ExamansDto> getExamansbyId(int examId);

	List<ExamansDto> getExamans();

	ExamansDto addExamans(ExamansDto ex);

	ExamansDto updateExamans(ExamansDto dto);

	int deleteExamans(int examansId);

}
