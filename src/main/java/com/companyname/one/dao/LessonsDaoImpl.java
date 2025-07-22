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
import com.companyname.one.security.TokenData;
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
	public List<Object[]> getLessons(String freeVideo,int languageId) {
	    Session session = sessionFactory.getCurrentSession();
	    String sqlData = "";
	    if(!"FREE".equals(freeVideo)) {
	    	TokenData data = User.getTokenData();

		    if("STUDENT".equals(data.getRole())) {
		    	sqlData = " SELECT l.lessonsId,ua.userAccountId,ua.name,lan.languagesId,lan.name AS lanName,l.youtube,l.pdf,l.date,lan.amount,l.freeVideo\r\n"
		    			+ "FROM courses c\r\n"
		    			+ "LEFT JOIN lessons  l ON l.languagesId = c.languagesId\r\n"
		    			+ "LEFT JOIN languages lan ON lan.languagesId = c.languagesId\r\n"
		    			+ "LEFT JOIN useraccount ua ON ua.userAccountId = c.userAccountId\r\n"
		    			+ "WHERE (c.studentId =  "+data.getUserId() + " OR l.freeVideo = 'FREE' ) ";
		    }else {
		    	sqlData = " SELECT l.lessonsId,ua.userAccountId, ua.name AS userName, lan.languagesId, lan.name AS lanName, "
		    			+ " l.youtube, l.pdf, l.date, lan.amount, l.freeVideo "
		    			+ " FROM lessons l  "
		    			+ " LEFT JOIN useraccount ua ON ua.userAccountId = l.userAccountId "
		    			+ " LEFT JOIN languages lan ON lan.languagesId = l.languagesId";
		    	sqlData = sqlData+" WHERE 1=1 ";
		    }
	    }else {
	    	sqlData = " SELECT l.lessonsId,ua.userAccountId, ua.name AS userName, lan.languagesId, lan.name AS lanName, "
	    			+ " l.youtube, l.pdf, l.date, lan.amount, l.freeVideo "
	    			+ " FROM lessons l  "
	    			+ " LEFT JOIN useraccount ua ON ua.userAccountId = l.userAccountId "
	    			+ " LEFT JOIN languages lan ON lan.languagesId = l.languagesId";
	    	sqlData = sqlData+" WHERE 1=1 ";
	    }

	    //if (freeVideo != null && !freeVideo.trim().isEmpty()) {
	    
	    if(!"ALL".equals(freeVideo)) {
	    	sqlData = sqlData+" AND l.freeVideo =  '"+freeVideo+"' ";
	    }
	    if(languageId>0) {
	    	sqlData = sqlData+" AND lan.languagesId =  "+languageId;
	    }
	    	//sqlData = sqlData+" GROUP BY l.lessonsId ORDER BY l.lessonsId DESC"; //only for my Hswut device

	    return session.createNativeQuery(sqlData).getResultList();
	}

	@Override
	public void addLessons(Lessons lessons) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(lessons);
//		session.createNativeQuery(
//		        "INSERT INTO useraccount VALUES lessonsId = :lessonsId"
//		    ).setParameter("lessons", lessons).executeUpdate();
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
