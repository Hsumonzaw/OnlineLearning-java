package com.companyname.one.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dao.CoursesDao;
import com.companyname.one.domain.Courses;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.util.ConvertDate;
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
        c.setCphoto(dto.getCphoto());
        c.setExamLink(dto.getExamLink());//new
        c.setPdf(dto.getPdf());//new
        c.setDescription(dto.getDescription());

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
        c.setCphoto(dto.getCphoto());
        c.setExamLink(dto.getExamLink());//new
        c.setPdf(dto.getPdf()); //new
        c.setDescription(dto.getDescription());

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
	@Transactional(readOnly=false)
	@Override
	public int updatePhoto(int coursesId, MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println(" file ");
		System.out.println(file);
		String oldPhoto = "";
		Courses c = courDao.getCoursesId(coursesId);
		oldPhoto = c.getCphoto();
		String cphoto = ConvertDate.convertyymmddhhmmss(new Date());
		c.setCphoto(cphoto);
		
		String pwd=new File("").getAbsolutePath();
		if(oldPhoto!=null) {
			File deleteFile=new File(pwd+"/coursephoto/"+oldPhoto+".png");
			deleteFile.delete();
		}
		
		
		File dir=new File(pwd+"/coursephoto/");
		String outPath=pwd+"/coursephoto/"+cphoto+".png";
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
		
		
		
		return coursesId;
		}
	
	@Transactional(readOnly=false)
	@Override
	public int updateFile(int coursesId, MultipartFile file) {
		// TODO Auto-generated method stub
		System.out.println(" file ");
		System.out.println(file);
		String oldFile = "";
		Courses c = courDao.getCoursesId(coursesId);
		oldFile = c.getPdf();
		String pdf = ConvertDate.convertyymmddhhmmss(new Date());
		c.setPdf(pdf);
		
		String pwd=new File("").getAbsolutePath();
		if(oldFile!=null) {
			File deleteFile=new File(pwd+"/coursefile/"+oldFile+".pdf");
			deleteFile.delete();
		}
		
		
		File dir=new File(pwd+"/coursefile/");
		String outPath=pwd+"/coursefile/"+pdf+".pdf";
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
		
		
		
		return coursesId;	}

}
