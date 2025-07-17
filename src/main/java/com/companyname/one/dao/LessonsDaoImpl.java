package com.companyname.one.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Examans;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.Lessons;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.util.User;

import lombok.var;

@Repository
public class LessonsDaoImpl implements LessonsDao{
	@Autowired
	SessionFactory sessionFactory;
//	@Override
//	public List<Object[]> getLessons(String freeVideo) {
//		// TODO Auto-generated method stub
//		Session session = sessionFactory.getCurrentSession();
//		List<Object[]> lessList = session.createNativeQuery("SELECT l.lessonsId,ua.name AS userName, lan.languagesId,lan.name AS lanName,l.youtube,l.pdf,l.date,lan.amount, l.freeVideo\r\n"
//				+ " FROM lessons l\r\n"
//				+ " LEFT JOIN useraccount ua ON ua.userAccountId = l.userAccountId\r\n"
//				+ " LEFT JOIN languages lan ON lan.languagesId = l.languagesId  WHERE l.freeVideo= :freeVideo\r\n")
//				.setParameter("freeVideo", freeVideo).getResultList();
//		return lessList;
//	}
	
	@Override
	public List<Object[]> getLessons(String freeVideo) {
	    Session session = sessionFactory.getCurrentSession();
	    
	    
	    
	    
	    StringBuilder sql = new StringBuilder();
	    sql.append("SELECT l.lessonsId,ua.userAccountId, ua.name AS userName, lan.languagesId, lan.name AS lanName, ");
	    sql.append("l.youtube, l.pdf, l.date, lan.amount, l.freeVideo ");
	    sql.append("FROM lessons l ");
	    sql.append("LEFT JOIN useraccount ua ON ua.userAccountId = l.userAccountId ");
	    sql.append("LEFT JOIN languages lan ON lan.languagesId = l.languagesId ");

	    if (freeVideo != null && !freeVideo.trim().isEmpty()) {
	        sql.append("WHERE l.freeVideo = :freeVideo ");
	    }

	    

	    // Optional: Add order by
	    sql.append("ORDER BY l.lessonsId DESC");

	    var query = session.createNativeQuery(sql.toString());

	    if (freeVideo != null && !freeVideo.trim().isEmpty()) {
	        query.setParameter("freeVideo", freeVideo);
	    }

	    return query.getResultList();
	}

	@Override
	public void addLessons(Lessons lessons) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(lessons);
	}
	@Override
	public void updateLessons( Lessons lessons) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(lessons);
	}
	@Override
	public void deleteLessons(int lessonsId) {
	    Session session = sessionFactory.getCurrentSession();

	    session.createNativeQuery(
	        "DELETE FROM comments WHERE lessonsId = :lessonsId"
	    ).setParameter("lessonsId", lessonsId).executeUpdate();

	    session.createNativeQuery(
	        "DELETE FROM lessons WHERE lessonsId = :lessonsId"
	    ).setParameter("lessonsId", lessonsId).executeUpdate();
	}

	@Override
	public Lessons getLessonsId(int lessonsId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.find(Lessons.class, lessonsId);
	}

//	@Override
//	public void deleteLessons(int lessonsId) {
//		// TODO Auto-generated method stub
//		Session session = sessionFactory.getCurrentSession();
//		session.createNativeQuery("Delete FROM lessons WHERE lessonsId=:lessonsId")
//		.setParameter("lessonsId", lessonsId).executeUpdate();
//		
//	}


}
