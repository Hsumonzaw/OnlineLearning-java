package com.companyname.one.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.LanguagesDao;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.UserAccountDto;

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
		lanDao.addLanguages(languages);
		dto.setLanguagesId(languages.getLanguagesId());
		return dto;
	}
	
	

	@Transactional(readOnly=false)
	@Override
	public LanguagesDto updateLanguage(LanguagesDto dto) {
		// TODO Auto-generated method stub
		Languages languages=new Languages(dto);
		lanDao.updateLanguage(languages);
		dto.setLanguagesId(languages.getLanguagesId());
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
