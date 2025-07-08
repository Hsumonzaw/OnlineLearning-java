package com.companyname.one.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.LessonsDao;
import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.Lessons;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.util.User;

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
			int languageId = Integer.parseInt(obj[2].toString());
			String lanName = (String)obj[3];
			String youtube = (String)obj[4];
			String pdf = (String)obj[5];
			Date date = (Date)obj[6];
			int amount = 0;
			if(obj[7]!=null)
					amount = Integer.parseInt(obj[7].toString());
			String ffreeVideo = (String)obj[8];

			LessonsDto dto = new LessonsDto(lessonsId,userName,languageId,lanName,youtube,pdf,date,amount,ffreeVideo);
			
			
			dtoList.add(dto);
		}
		return dtoList;
	}
	@Transactional(readOnly=false)
	@Override
	public LessonsDto addLessons(LessonsDto dto) {
		// TODO Auto-generated method stub
		Lessons less =new Lessons();
		less.setUserAccountId(User.getUserId());
        less.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
        less.setYoutube(dto.getYoutube());
        less.setPdf(dto.getPdf());
		less.setDate(new Date());      
		less.setModifiedDate(new Date()); 
		less.setFreeVideo("Free");		
		lessDao.addLessons(less);
		return dto;
		}
	@Transactional(readOnly=false)
	@Override
	public LessonsDto updateLessons(LessonsDto dto) {
		// TODO Auto-generated method stub
		Lessons less =new Lessons();
		less.setLessonsId(dto.getLessonsId());
		less.setUserAccountId(User.getUserId());
        less.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
        less.setYoutube(dto.getYoutube());
        less.setPdf(dto.getPdf());
		less.setDate(new Date());      
		less.setModifiedDate(new Date()); 
		less.setFreeVideo("Free");		
		lessDao.updateLessons(less);
		return dto;
		}
	@Override
	@Transactional(readOnly=false)
	public int deleteLessons(int lessonsId) {
		// TODO Auto-generated method stub
		lessDao.deleteLessons(lessonsId);
		return lessonsId;
	}
	

}
