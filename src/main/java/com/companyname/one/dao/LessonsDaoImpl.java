package com.companyname.one.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.Lessons;
import com.companyname.one.domain.UserAccount;

@Repository
public class LessonsDaoImpl implements LessonsDao{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public List<Object[]> getLessons(String freeVideo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> lessList = session.createNativeQuery("SELECT l.lessonsId,ua.name AS userName,lan.name AS lanName,l.youtube,l.pdf,l.date,lan.amount\r\n"
				+ " FROM lessons l\r\n"
				+ " LEFT JOIN useraccount ua ON ua.userAccountId = l.userAccountId\r\n"
				+ " LEFT JOIN languages lan ON lan.languagesId = l.languagesId "
				+ " WHERE l.freeVideo=:freeVideo ")
				.setParameter("freeVideo", freeVideo).getResultList();
		return lessList;
	}

}
