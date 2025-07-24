package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.Languages;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.security.TokenData;
import com.companyname.one.util.User;


@Repository
public class LanguagesDaoImpl implements LanguagesDao{
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<LanguagesDto> getLanguages(int index) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		TokenData data = new TokenData();
		try {
			data = User.getTokenData();
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		
		List<LanguagesDto> dtoList = new ArrayList<LanguagesDto>();
		List<Object[]> userList;
//
//	    if("TEACHER".equals(data.getRole())) {
//	    	if(index==0) {
//	    		userList = session.createNativeQuery("SELECT l.languagesId,l.name,l.amount,l.examLink,l.examFee\r\n"
//						+ "FROM languages l\r\n"
//						+ "").getResultList();
//	    	}else {
//	    		userList = session.createNativeQuery(
//		    		    "SELECT lan.languagesId,\r\n"
//		    		    + "COALESCE(MIN(les.lessonsId), 0) AS lessonsId,\r\n"
//		    		    + "lan.name, lan.amount, lan.examLink, lan.examFee,\r\n"
//		    		    + "lan.userAccountId\r\n"
//		    		    + "FROM languages lan\r\n"
//		    		    + "LEFT JOIN lessons les ON les.languagesId = lan.languagesId\r\n"
//		    		    + "WHERE lan.userAccountId = :userId\r\n"
//		    		    + "GROUP BY lan.languagesId, lan.name, lan.amount, lan.examLink, lan.examFee, lan.userAccountId\r\n"
//		    		    + "")
//		    		.setParameter("userId", data.getUserId())
//		    		.getResultList();
//
//				for(Object[] obj:userList) {
//					int languagesId = Integer.parseInt(obj[0].toString());
//					int lessonsId = Integer.parseInt(obj[1].toString());			
//					String name = (String)obj[2];
//					int amount = Integer.parseInt(obj[3].toString());
//					String examLink = (String)obj[4];
//					int examFee = Integer.parseInt(obj[5].toString());
//					int userAccountId = Integer.parseInt(obj[6].toString());
//					LanguagesDto dto = new LanguagesDto(languagesId,name,amount,examLink,examFee);
//					dto.setLessonsDto(new LessonsDto(lessonsId,userAccountId));				
//					dtoList.add(dto);
//				}
//				return dtoList;
//	    	}
//	    	
//	    	
//	    }else {
	    	userList = session.createNativeQuery("SELECT l.languagesId,l.name,l.amount,l.examLink,l.examFee,SUM(IF(c.studentId=10,1,0)) AS buy,SUM(IF(c.studentId=10,c.coursesId,0)) AS coursesId\r\n"
					+ "FROM languages l "
					+ " LEFT JOIN courses c ON c.languagesId = l.languagesId "
					+ " GROUP BY l.languagesId \r\n"
					+ "").getResultList();
//	    	}
		
		for(Object[] obj: userList) {
			int languagesId = Integer.parseInt(obj[0].toString());			
			String name = (String)obj[1];
			int amount = Integer.parseInt(obj[2].toString());
			String examLink = (String)obj[3];
			int examFee = Integer.parseInt(obj[4].toString());
			int buy = Integer.parseInt(obj[5].toString());
			int coursesId = Integer.parseInt(obj[6].toString());
			LanguagesDto dto = new LanguagesDto(languagesId,name,amount,examLink,examFee);	
			dto.setBuy(buy);
			dto.setCoursesId(coursesId);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public void addLanguages(Languages languages) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(languages);
	}

	@Override
	public void updateLanguage(Languages languages) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(languages);
	}

	@Override
	public void deleteLanguage(int languagesId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery(
		        "DELETE FROM lessons WHERE languagesId = :languagesId"
		    ).setParameter("languagesId", languagesId).executeUpdate();
		session.createNativeQuery(
		        "DELETE FROM courses WHERE languagesId = :languagesId"
		    ).setParameter("languagesId", languagesId).executeUpdate();
		
		session.createNativeQuery("Delete FROM languages WHERE languagesId=:languagesId")
		.setParameter("languagesId", languagesId).executeUpdate();
	}
	
	
}
