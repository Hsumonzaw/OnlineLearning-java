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
import com.companyname.one.domain.Ans;
import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Examans;
import com.companyname.one.domain.Quiz;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.AnsDto;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.QuizDto;
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
	@Transactional(readOnly=true)
	@Override
	public List<QuizDto> getQuiz() {
		// TODO Auto-generated method stub
		
		return examDao.getQuiz();
	}
	@Transactional(readOnly=false)
	@Override
	public int addQuiz(QuizDto dto) {
		// TODO Auto-generated method stub
		Quiz q = new Quiz(dto);
		examDao.addQuiz(q);
		return q.getQuizId();
	}
	@Transactional(readOnly=false)
	@Override
	public int updateQuiz(QuizDto dto) {
		// TODO Auto-generated method stub
		Quiz q = new Quiz(dto);
		examDao.updateQuiz(q);
		return q.getQuizId();
	}
	@Transactional(readOnly=false)
	@Override
	public int deleteQuiz(int quizId) {
		// TODO Auto-generated method stub
		examDao.deleteQuiz(new Quiz(quizId));
		return quizId;
	}
	@Transactional(readOnly=true)
	@Override
	public List<AnsDto> getAns() {
		// TODO Auto-generated method stub
		return examDao.getAns();
	}
	@Transactional(readOnly=false)
	@Override
	public List<QuizDto> getQuizStudent(int languagesId) {
		// TODO Auto-generated method stub
		return examDao.getQuizStudent(languagesId);
	}
	@Transactional(readOnly=false)
	@Override
	public int saveAns(int coursesId,List<QuizDto> dtoList,int minutesCount) {
		// TODO Auto-generated method stub
		int count = 0;
		for(QuizDto dto:dtoList) {
			if(dto.getCorrect()==dto.getAns()) {
				count+=1;
			}
		}
		double doubleInt = ((double)count /dtoList.size())*100;
		int examMark = (int) doubleInt;
		Examans ex = examDao.getExamAnsByCoursesId(coursesId);
		//Examans ex = new Examans();
		ex.setUserAccountId(User.getUserId());
		ex.setCoursesId(coursesId);
		ex.setExamMark(examMark);
        ex.setDate(new Date());
        ex.setStatus("PENDING");
        ex.setMinutesCount(minutesCount);
        examDao.addExamans(ex);
        examDao.deleteExam(ex.getExamId());
        for(QuizDto dto:dtoList) {
        	Ans ans = new Ans();
        	ans.setQuizId(dto.getQuizId());
        	ans.setExamId(ex.getExamId());
        	ans.setAns(dto.getAns());
			examDao.saveAns(ans);
		}
		return 1;
	}
	@Transactional(readOnly=true)
	@Override
	public int getExamMark(int languagesId) {
		// TODO Auto-generated method stub
		return examDao.getExamMark(languagesId);
	}
	
	}
	


