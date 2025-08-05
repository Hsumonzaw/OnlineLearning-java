package com.companyname.one.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dao.LanguagesDao;
import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.util.ConvertDate;
import com.companyname.one.util.User;

@Service
public class LanguagesServiceImpl implements LanguagesService{
	@Autowired
	
	LanguagesDao lanDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<LanguagesDto> getLanguages(int index) {
		// TODO Auto-generated method stub
//		List<Languages> lanList = lanDao.getLanguages();
//		List<LanguagesDto> lanDtoList = new ArrayList<>();
//		for(Languages language:lanList) {
//			LanguagesDto dto = new LanguagesDto(language);
//			lanDtoList.add(dto);
//		}
//		return lanDtoList;
		return lanDao.getLanguages(index);
		
			}

	@Transactional(readOnly=false)
	@Override
	public LanguagesDto addLanguages(LanguagesDto dto) {
		// TODO Auto-generated method stub
		Languages languages =new Languages(dto);
//		languages.setLanguagesId(dto.getLanguagesId());
		if(dto.getLessonsDto()!=null) {
			languages.setLessonsId(dto.getLessonsDto().getLessonsId());
		}
		
		languages.setUserAccountId(User.getUserId());
		languages.setName(dto.getName());
		languages.setLanPhoto(dto.getLanPhoto());
		languages.setAmount(dto.getAmount());
		languages.setExamFee(dto.getExamFee());
		languages.setPdf(dto.getPdf());
		languages.setDescription(dto.getDescription());
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
		languages.setLanPhoto(dto.getLanPhoto());
		languages.setAmount(dto.getAmount());
		languages.setExamFee(dto.getExamFee());
		languages.setPdf(dto.getPdf());
		languages.setDescription(dto.getDescription());
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
	
	@Transactional(readOnly=false)
	@Override
	public int updateLanPhoto(int languagesId, MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println(" file ");
		System.out.println(file);
		String oldPhoto = "";
		Languages c = lanDao.getLanguagesId(languagesId);
		oldPhoto = c.getLanPhoto();
		String lanPhoto = ConvertDate.convertyymmddhhmmss(new Date());
		c.setLanPhoto(lanPhoto);
		
		String pwd=new File("").getAbsolutePath();
		if(oldPhoto!=null) {
			File deleteFile=new File(pwd+"/languagephoto/"+oldPhoto+".png");
			deleteFile.delete();
		}
		
		
		File dir=new File(pwd+"/languagephoto/");
		String outPath=pwd+"/languagephoto/"+lanPhoto+".png";
		File dest=new File(outPath);
		try {
			if (!dir.exists()) {
				dir.mkdir();
			}
			file.transferTo(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return languagesId;	
		}
	
	@Transactional(readOnly=false)
	@Override
	public int updateLanFile(int languagesId, MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println(" file ");
		System.out.println(file);
		String oldFile = "";
		Languages c = lanDao.getLanguagesId(languagesId);
		oldFile = c.getPdf();
		String pdf = ConvertDate.convertyymmddhhmmss(new Date());
		c.setPdf(pdf);
		
		String pwd=new File("").getAbsolutePath();
		if(oldFile!=null) {
			File deleteFile=new File(pwd+"/languagefile/"+oldFile+".pdf");
			deleteFile.delete();
		}
		
		
		File dir=new File(pwd+"/languagefile/");
		String outPath=pwd+"/languagefile/"+pdf+".pdf";
		File dest=new File(outPath);
		try {
			if (!dir.exists()) {
				dir.mkdir();
			}
			file.transferTo(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return languagesId;	
		}
	}
	

