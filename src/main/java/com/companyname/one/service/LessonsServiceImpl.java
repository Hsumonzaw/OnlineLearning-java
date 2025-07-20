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

import com.companyname.one.dao.LessonsDao;
import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.Lessons;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.util.ConvertDate;
import com.companyname.one.util.User;

@Service
public class LessonsServiceImpl implements LessonsService{
	@Autowired
	LessonsDao lessDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<LessonsDto> getLessons(String freeVideo,int languageId) {
		// TODO Auto-generated method stub
//		List<Lessons> lessList = lessDao.getLessons(freeVideo);
//		List<LessonsDto> dtoList = new ArrayList<LessonsDto>();
//		for(Lessons less:lessList) {
//			LessonsDto dto = new LessonsDto(less);
//			dtoList.add(dto);
//		}
		List<Object[]> objList = lessDao.getLessons(freeVideo,languageId);
		List<LessonsDto> dtoList = new ArrayList<LessonsDto>();
		for(Object[] obj:objList) {
			int lessonsId = Integer.parseInt(obj[0].toString());
			int userAccountId = Integer.parseInt(obj[1].toString());
			String userName = (String)obj[2];//for save and update error 
			languageId = Integer.parseInt(obj[3].toString());
			String lanName = (String)obj[4];
			String youtube = (String)obj[5];
			String pdf = (String)obj[6];
			Date date = (Date)obj[7];
			Date modifiedDate = new Date();
			int amount = 0;
			if(obj[8]!=null)
					amount = Integer.parseInt(obj[8].toString());
			String ffreeVideo = (String)obj[9];
//			LessonsDto dto = new LessonsDto(lessonsId,userName,languageId,lanName,youtube,pdf,date,modifiedDate,amount,ffreeVideo);
			
			LessonsDto dto = new LessonsDto();
			dto.setLessonsId(lessonsId);
			
			UserAccountDto userDto = new UserAccountDto();
			userDto.setUserAccountId(userAccountId);
			userDto.setUserName(userName);//for save and update error 
			dto.setUserAccount(userDto); 
			//dto.setUserAccount(userName);
			dto.setYoutube(youtube);
			dto.setPdf(pdf);
			dto.setDate(date);
			dto.setModifiedDate(modifiedDate);
			dto.setFreeVideo(ffreeVideo);

			// 🔑 Create and assign nested LanguagesDto
			LanguagesDto langDto = new LanguagesDto();
			langDto.setLanguagesId(languageId);
			langDto.setName(lanName);
			langDto.setAmount(amount);

			dto.setLanguagesDto(langDto);

			
			dtoList.add(dto);
		}
		return dtoList;
	}
	@Transactional(readOnly=false)
	@Override
	public LessonsDto addLessons(LessonsDto dto) {
		// TODO Auto-generated method stub
		Lessons less =new Lessons();
		less.setUserAccountId(User.getUserId());//for save and update error 
        less.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
        less.setYoutube(dto.getYoutube());
        less.setPdf(dto.getPdf());
		less.setDate(new Date());      
		less.setModifiedDate(new Date()); 
		less.setFreeVideo(dto.getFreeVideo());		
		lessDao.addLessons(less);
		return dto;
		}
	@Transactional(readOnly=false)
	@Override
	public LessonsDto updateLessons(LessonsDto dto) {
		// TODO Auto-generated method stub
		Lessons less =new Lessons();
		less.setLessonsId(dto.getLessonsId());
		less.setUserAccountId(User.getUserId());//for save and update error 
        less.setLanguagesId(dto.getLanguagesDto().getLanguagesId());
        less.setYoutube(dto.getYoutube());
        less.setPdf(dto.getPdf());
		less.setDate(new Date());      
		less.setModifiedDate(new Date()); 
		less.setFreeVideo(dto.getFreeVideo());		
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
	@Transactional(readOnly=false)
	@Override
	public int updateFile(int lessonsId, MultipartFile file) {
		// TODO Auto-generated method stub
		String oldFile = "";
		Lessons less = lessDao.getLessonsId(lessonsId);
		oldFile = less.getPdf();
		String pdf = ConvertDate.convertyymmddhhmmss(new Date());
		less.setPdf(pdf);
		
		String pwd=new File("").getAbsolutePath();
		if(oldFile!=null) {
			File deleteFile=new File(pwd+"/lessonPdf/"+oldFile+".pdf");
			deleteFile.delete();
		}
		
		
		File dir=new File(pwd+"/lessonPdf/");
		String outPath=pwd+"/lessonPdf/"+pdf+".pdf";
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
		
		
		
		return lessonsId;

	}
	

}
