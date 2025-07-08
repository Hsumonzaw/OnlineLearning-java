package com.companyname.one.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.CoursesDao;
import com.companyname.one.domain.Courses;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.util.User;


@Service
public class CoursesServiceImpl implements CoursesService{
	@Autowired
	
	CoursesDao courDao;
	
	
	@Transactional(readOnly=true)
	@Override
	public List<CoursesDto> getCourses() {
		// TODO Auto-generated method stub
		return courDao.getCourses();
	}

	@Transactional(readOnly=false)
	@Override
	public CoursesDto addCourses(CoursesDto dto) {
		// TODO Auto-generated method stub
		Courses c = new Courses();
		c.setUserAccountId(User.getUserId());
        c.setStudentId(dto.getStudentDto().getUserAccountId());
        c.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
        c.setType(dto.getType());
        c.setAmount(dto.getAmount());
        c.setReceivedDate(dto.getReceivedDate());
        c.setDate(new Date());
        c.setModifiedDate(new Date());
        courDao.addCourses(c);

       
        return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public CoursesDto updateCourse(CoursesDto dto) {
		// TODO Auto-generated method stub
		Courses c = new Courses();
		c.setCoursesId(dto.getCoursesId());
		c.setUserAccountId(User.getUserId());
        c.setStudentId(dto.getStudentDto().getUserAccountId());
        c.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
        c.setType(dto.getType());
        c.setAmount(dto.getAmount());
        c.setReceivedDate(dto.getReceivedDate());
        c.setDate(dto.getDate());
        c.setModifiedDate(new Date());
        courDao.updateCourse(c);

        return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteCourse(int coursesId) {
		// TODO Auto-generated method stub
		courDao.deleteCourse(coursesId);
		return coursesId;
	}

}
