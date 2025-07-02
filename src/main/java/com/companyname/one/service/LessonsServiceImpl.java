package com.companyname.one.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.LessonsDao;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.Lessons;
import com.companyname.one.dto.LessonsDto;

@Service
public class LessonsServiceImpl implements LessonsService{
	@Autowired
	LessonsDao lessDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<LessonsDto> getLessons(String freeVideo) {
		// TODO Auto-generated method stub
//		List<Lessons> lessList = lessDao.getLessons(freeVideo);
//		List<LessonsDto> dtoList = new ArrayList<LessonsDto>();
//		for(Lessons less:lessList) {
//			LessonsDto dto = new LessonsDto(less);
//			dtoList.add(dto);
//		}
		List<Object[]> objList = lessDao.getLessons(freeVideo);
		List<LessonsDto> dtoList = new ArrayList<LessonsDto>();
		for(Object[] obj:objList) {
			int lessonsId = Integer.parseInt(obj[0].toString());
			String userName = (String)obj[1];
			String lanName = (String)obj[2];
			String youtube = (String)obj[3];
			String pdf = (String)obj[4];
			Date date = (Date)obj[5];
			int amount = 0;
			if(obj[6]!=null)
					amount = Integer.parseInt(obj[6].toString());
			LessonsDto dto = new LessonsDto(lessonsId,userName,lanName,youtube,pdf,amount);
			
			dtoList.add(dto);
		}
		return dtoList;
	}
	@Transactional(readOnly=false)
	@Override
	public LessonsDto addLessons(LessonsDto dto) {
		// TODO Auto-generated method stub
		Lessons lessons =new Lessons(dto);
		lessons.setDate(new Date());      
		lessons.setModifiedDate(new Date()); 
		lessDao.addLessons(lessons);
		dto.setLessonsId(lessons.getLessonsId());
		return dto;	}

}
