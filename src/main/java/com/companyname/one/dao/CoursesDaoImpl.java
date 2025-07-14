package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Lessons;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.UserAccountDto;

@Repository
public class CoursesDaoImpl implements CoursesDao{
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<CoursesDto> getCourses() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> objList = session.createNativeQuery("SELECT c.coursesId,c.userAccountId,ua.name AS useraccountName,c.studentId,uac.name AS studentName,\r\n"
				+ "c.languagesId,l.name AS languagesName,c.`type`,c.amount,c.cphoto,\r\n"
				+ "c.receivedDate,c.date,c.modifiedDate\r\n"
				+ "FROM courses c\r\n"
				+ "LEFT JOIN useraccount ua ON ua.userAccountId = c.userAccountId\r\n"
				+ "LEFT JOIN useraccount uac ON uac.userAccountId = c.studentId\r\n"
				+ "LEFT JOIN languages l ON l.languagesId = c.languagesId\r\n"
				+ "ORDER BY c.receivedDate").getResultList();
		List<CoursesDto> dtoList = new ArrayList<CoursesDto>();
		
		for(Object[] obj:objList) {
			int coursesId = Integer.parseInt(obj[0].toString());
			
			int userAccountId = Integer.parseInt(obj[1].toString());
			String userAccountName = (String)obj[2];
			int studentId = Integer.parseInt(obj[3].toString());
			String studentName = (String)obj[4];
			int languagesId = Integer.parseInt(obj[5].toString());
			String languagesName = (String)obj[6];
			
			String type = (String)obj[7];
			
			int amount = Integer.parseInt(obj[8].toString());
			String cphoto = (String)obj[9];

			Date receivedDate = (Date)(obj[10]);
			Date date = (Date)(obj[11]);
			Date modifiedDate = (Date)(obj[12]);

			CoursesDto dto = new CoursesDto(coursesId,type,amount,cphoto,receivedDate,date,modifiedDate);
			
			dto.setUserAccountDto(new UserAccountDto(userAccountId,userAccountName));
			
			dto.setStudentDto(new UserAccountDto(studentId,studentName));
			
			dto.setLanguagesDto(new LanguagesDto(languagesId,languagesName));
			
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public void addCourses(Courses c) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(c);
	}

	@Override
	public void updateCourse(Courses c) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(c);
	}
	@Override
	public void deleteCourse(int coursesId) {
	    Session session = sessionFactory.getCurrentSession();

	    

	    

	    session.createNativeQuery(
	        "DELETE FROM examAns WHERE coursesId = :coursesId"
	    ).setParameter("coursesId", coursesId).executeUpdate();

	    session.createNativeQuery(
	        "DELETE FROM courses WHERE coursesId = :coursesId"
	    ).setParameter("coursesId", coursesId).executeUpdate();
	}

	@Override
	public Courses getCoursesId(int coursesId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return session.find(Courses.class, coursesId);	}


//	@Override
//	public void deleteCourse(int coursesId) {
//		// TODO Auto-generated method stub
//		Session session = sessionFactory.getCurrentSession();
//		session.createNativeQuery("Delete FROM courses WHERE coursesId=:coursesId")
//		.setParameter("coursesId", coursesId).executeUpdate();
//	}

}
