package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.companyname.one.domain.Examans;
import com.companyname.one.domain.Languages;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.UserAccountDto;

@Repository
public class ExamansDaoImpl implements ExamansDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<ExamansDto> getExamans() {
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> examList = session.createNativeQuery("SELECT ex.examId, ua.name, c.languagesId,l.name AS languagesName, ex.pdf,ex.examMark, ex.date, ex.status FROM examans ex\r\n"
				+ "LEFT JOIN useraccount ua ON ex.userAccountId = ua.userAccountId\r\n"
				+ "LEFT JOIN languages l ON l.languagesId = c.languagesId\r\n"
				+ "LEFT JOIN courses c ON ex.coursesId = c.coursesId").getResultList();
		List<ExamansDto> dtoList = new ArrayList<ExamansDto>();
		
		for(Object[] obj:examList) {
			int examId = Integer.parseInt(obj[0].toString());
			
			String name = (String)obj[1];
			int languagesId = Integer.parseInt(obj[2].toString());

			//String languagesName = (String)obj[3];
			String pdf = (String)obj[3];
			int examMark = Integer.parseInt(obj[4].toString());
			Date date = (Date)(obj[5]);
			String status = (String)obj[6];
			//String languagesName = (String)obj[];
			


			ExamansDto dto = new ExamansDto(examId,pdf,examMark,date,status);
			
			dto.setUserAccountDto(new UserAccountDto(name));
			
			//.setLanguagesDto(new LanguagesDto(languagesId, languagesName));

			
			dto.setCoursesDto(new CoursesDto(languagesId));
			

			
			dtoList.add(dto);
		}
		return dtoList;
		
	}

	@Override
	public void addExamans(Examans ex) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(ex);
	}

	@Override
	public void updateExamans(Examans ex) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(ex);
	}

	@Override
	public void deleteExamans(int examansId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.createNativeQuery("Delete FROM examans WHERE examId=:examId")
		.setParameter("examId", examansId).executeUpdate();
		
	}
		
	}


