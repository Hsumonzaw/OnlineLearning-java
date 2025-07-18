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
import com.companyname.one.dto.UserAccountDto;


@Repository
public class LanguagesDaoImpl implements LanguagesDao{
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<LanguagesDto> getLanguages() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
//		List<Object[]> userList = session.createNativeQuery("SELECT l.languagesId,l.name,l.amount,l.examLink,l.examFee,ua.userAccountId\r\n"
//				+ "FROM languages l\r\n"
//				+ "LEFT JOIN useraccount ua ON ua.userAccountId = l.userAccountId\r\n"
//				+ "").getResultList();
		List<Object[]> userList = session.createNativeQuery("SELECT l.languagesId,l.name,l.amount,l.examLink,l.examFee\r\n"
				+ "FROM languages l\r\n"
				+ "").getResultList();
		
		List<LanguagesDto> dtoList = new ArrayList<LanguagesDto>();
		
		for(Object[] obj:userList) {
			int languagesId = Integer.parseInt(obj[0].toString());
			
			
			String name = (String)obj[1];
			int amount = Integer.parseInt(obj[2].toString());
			String examLink = (String)obj[3];
			int examFee = Integer.parseInt(obj[4].toString());
//			int userAccountId = 0;
//			if(obj[5] != null) {
//				userAccountId = Integer.parseInt(obj[5].toString());
//			}
			
			
			LanguagesDto dto = new LanguagesDto(languagesId,name,amount,examLink,examFee);

			
//			dto.setUserAccount(new UserAccountDto(userAccountId));
			
			
			
			
			
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
		session.createNativeQuery("Delete FROM languages WHERE languagesId=:languagesId")
		.setParameter("languagesId", languagesId).executeUpdate();
	}
	
	
}
