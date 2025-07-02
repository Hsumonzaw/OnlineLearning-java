package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Examans;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.ExamansDto;

public interface ExamansDao {

	//List<Object[]> getExamansbyId(int examId);

	List<ExamansDto> getExamans();

	void addExamans(Examans ex);

	void updateExamans(Examans ex);

	void deleteExamans(int examansId);

}
