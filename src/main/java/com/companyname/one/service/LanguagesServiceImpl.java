package com.companyname.one.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.LanguagesDao;
import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.util.User;

@Service
public class LanguagesServiceImpl implements LanguagesService{
	@Autowired
	
	LanguagesDao lanDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<LanguagesDto> getLanguages() {
		// TODO Auto-generated method stub
//		List<Languages> lanList = lanDao.getLanguages();
//		List<LanguagesDto> lanDtoList = new ArrayList<>();
//		for(Languages language:lanList) {
//			LanguagesDto dto = new LanguagesDto(language);
//			lanDtoList.add(dto);
//		}
//		return lanDtoList;
		return lanDao.getLanguages();
		
			}

	@Transactional(readOnly=false)
	@Override
	public LanguagesDto addLanguages(LanguagesDto dto) {
		// TODO Auto-generated method stub
		Languages languages =new Languages(dto);
//		languages.setLanguagesId(dto.getLanguagesId());
		languages.setLessonsId(dto.getLessonsDto().getLessonsId());
		languages.setUserAccountId(User.getUserId());
		languages.setName(dto.getName());
		languages.setAmount(dto.getAmount());
		languages.setExamLink(dto.getExamLink());
		languages.setExamFee(dto.getExamFee());
		lanDao.addLanguages(languages);
		return dto;
		
       

	}
	
	

	@Transactional(readOnly=false)
	@Override
	public LanguagesDto updateLanguage(LanguagesDto dto) {
		// TODO Auto-generated method stub
		Languages languages=new Languages(dto);
		//dto.setLanguagesId(languages.getLanguagesId());
		languages.setLanguagesId(dto.getLanguagesId());
		languages.setLessonsId(dto.getLessonsDto().getLessonsId());
		languages.setUserAccountId(User.getUserId());
		languages.setName(dto.getName());
		languages.setAmount(dto.getAmount());
		languages.setExamLink(dto.getExamLink());
		languages.setExamFee(dto.getExamFee());
		lanDao.updateLanguage(languages);
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteLanguage(int languagesId) {
		// TODO Auto-generated method stub
		lanDao.deleteLanguage(languagesId);
		return languagesId;
	}
	
}
