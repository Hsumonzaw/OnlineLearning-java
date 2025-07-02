package com.companyname.one.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.ExamansDao;
import com.companyname.one.dao.UserAccountDao;
import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Examans;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.util.User;
@Service
public class ExamansServiceImpl implements ExamansService{
	@Autowired
	ExamansDao examDao;
	
	@Transactional(readOnly=true)

	@Override
	public List<ExamansDto> getExamans() {
		// TODO Auto-generated method stub
		return examDao.getExamans();
	}
	@Transactional(readOnly=false)
	@Override
	public ExamansDto addExamans(ExamansDto dto) {
		Examans ex = new Examans();
		ex.setUserAccountId(User.getUserId());
		//ex.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
		ex.setCoursesId(dto.getCoursesDto().getCoursesId());
		ex.setPdf(dto.getPdf());
		ex.setExamMark(dto.getExamMark());
        ex.setDate(new Date());
        ex.setStatus(dto.getStatus());
        examDao.addExamans(ex);
        return dto;
	}
	@Transactional(readOnly=false)
	@Override
	public ExamansDto updateExamans(ExamansDto dto) {
		// TODO Auto-generated method stub
		Examans ex = new Examans();
		ex.setExamId(dto.getExamId());

		ex.setUserAccountId(User.getUserId());
		//ex.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
		ex.setCoursesId(dto.getCoursesDto().getCoursesId());
		ex.setPdf(dto.getPdf());
		ex.setExamMark(dto.getExamMark());
        ex.setDate(new Date());
        ex.setStatus(dto.getStatus());
        examDao.updateExamans(ex);
        return dto;
	}
	
	@Transactional(readOnly=false)
	@Override
	public int deleteExamans(int examansId) {
		// TODO Auto-generated method stub
		examDao.deleteExamans(examansId);
		return examansId;
	}
	
	}
	


