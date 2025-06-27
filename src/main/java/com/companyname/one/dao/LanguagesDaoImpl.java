package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.Languages;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.LanguagesDto;


@Repository
public class LanguagesDaoImpl implements LanguagesDao{
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Languages> getLanguages() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Languages> userList = session.createQuery("SELECT l FROM Languages l ORDER BY l.name ASC ").getResultList();
		
		return userList;
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
