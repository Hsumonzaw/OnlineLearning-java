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
import com.companyname.one.domain.Quiz;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.AnsDto;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.QuizDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.security.TokenData;
import com.companyname.one.util.User;

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

	@Override
	public List<QuizDto> getQuiz() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> objList = session.createNativeQuery("SELECT q.quizId,l.languagesId,l.name AS lname,ua.name AS uaNmae,"
				+ "q.name,q.date,q.modifiedDate,q.ansone,q.anstwo,q.ansthree,q.correct\r\n"
				+ "FROM quiz	 q\r\n"
				+ "LEFT JOIN languages l ON l.languagesId = q.languagesId\r\n"
				+ "LEFT JOIN useraccount ua ON ua.userAccountId = q.userAccountId\r\n"
				+ " ").setParameter("userAccountId", User.getUserId()).getResultList();
		List<QuizDto> dtoList = new ArrayList<QuizDto>();
		
		for(Object[] obj:objList) {
			int quizId = Integer.parseInt(obj[0].toString());
			
			int languagesId = Integer.parseInt(obj[1].toString());
			String lName = (String)obj[2];
			String uaName = (String)obj[3];
			String qName = (String)obj[4];
			Date date = (Date)obj[5];
			Date modifiedDate = (Date)obj[6];
			String ansone = (String)obj[7];
			String anstwo = (String)obj[8];
			String ansthree = (String)obj[9];
			int correct = Integer.parseInt(obj[10].toString());
			
			QuizDto dto = new QuizDto(quizId,languagesId,lName,uaName,qName,date,modifiedDate,ansone,anstwo,ansthree,correct);
			dtoList.add(dto);
			
			
		}
		return dtoList;
		}

	@Override
	public void addQuiz(Quiz q) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(q);
	}

	@Override
	public void updateQuiz(Quiz q) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(q);
	}

	@Override
	public void deleteQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(quiz);
	}

	
	@Override
	public List<AnsDto> getAns() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		TokenData data = User.getTokenData();
		List<Object[]> objList = new ArrayList<Object[]>();
		if("STUDENT".equals(data.getUserId())) {
			objList = session.createNativeQuery(""
					+ "SELECT a.ansId,a.ans,a.quizId,l.name AS lName,ua.name AS uaName\r\n"
					+ "q.ansone,q.anstwo,q.ansthree,q.correct,q.name AS qName\r\n"
					+ "FROM ans a\r\n"
					+ "LEFT JOIN examans ex ON ex.examId = a.examId\r\n"
					+ "LEFT JOIN quiz q ON q.quizId = a.quizId\r\n"
					+ "LEFT JOIN useraccount ua ON ua.userAccountId = ex.userAccountId\r\n"
					+ "LEFT JOIN languages l ON l.languagesId = q.languagesId\r\n"
					+" WHERE ex.userAccountId=:studentId "
					).setParameter("studentId", data.getUserId()).getResultList();
		}else {
			objList = session.createNativeQuery(""
					+ "SELECT a.ansId,a.ans,a.quizId,l.name AS lName,ua.name AS uaName\r\n"
					+ "q.ansone,q.anstwo,q.ansthree,q.correct,q.name AS qName\r\n"
					+ "FROM ans a\r\n"
					+ "LEFT JOIN examans ex ON ex.examId = a.examId\r\n"
					+ "LEFT JOIN quiz q ON q.quizId = a.quizId\r\n"
					+ "LEFT JOIN useraccount ua ON ua.userAccountId = ex.userAccountId\r\n"
					+ "LEFT JOIN languages l ON l.languagesId = q.languagesId\r\n"
					).getResultList();
		}
		
		List<AnsDto> dtoList = new ArrayList<AnsDto>();
		int totalAns = 0;
		for(Object[] obj:objList) {
			int ansId = Integer.parseInt(obj[0].toString());
			
			int ansNumber = Integer.parseInt(obj[1].toString());
			int quizId = Integer.parseInt(obj[2].toString());
			String lName = (String)obj[3];
			String uaName = (String)obj[4];
			String ansone = (String)obj[5];
			String anstwo = (String)obj[6];
			String ansthree = (String)obj[7];
			int correct = Integer.parseInt(obj[8].toString());
			String qName = (String)obj[9];
			int isTrue = 0;
			if(ansNumber==correct) {
				isTrue = 1;
				totalAns+=10;
			}
			AnsDto dto = new AnsDto(ansId,ansNumber,quizId,lName,uaName,ansone,anstwo,ansthree,correct,qName);
			dto.setCorrect(isTrue);
			dto.setTotalAns(totalAns);
			dtoList.add(dto);
			
			
		}
		return dtoList;
	}
	}


