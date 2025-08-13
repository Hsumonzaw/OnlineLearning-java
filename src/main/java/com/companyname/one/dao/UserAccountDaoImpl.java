package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.security.TokenData;
import com.companyname.one.util.Cryption;
import com.companyname.one.util.User;
@Repository
public class UserAccountDaoImpl implements UserAccountDao{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public UserAccount getUserAccountsById(int userId) {
		// TODO Auto-generated method stub\
		Session session = sessionFactory.getCurrentSession();
		return session.find(UserAccount.class, userId);
	}

	@Override
	public List<UserAccountDto> getUserAccounts(String userType) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> allList = null;	
		List<Object[]> userList = null;

		

		List<UserAccountDto> dtoList = new ArrayList<UserAccountDto>();
		
		 if("TE".equals(userType)) {
				List<UserAccount> userListOne  = session.createQuery("SELECT ua FROM UserAccount ua "
						+ " Where ua.status=1 AND ua.userType='TEACHER' "
						+ " ORDER BY ua.name ASC ").getResultList();
				for(UserAccount ua:userListOne) {
					UserAccountDto dto = new UserAccountDto(ua);
					dtoList.add(dto);
				}
			}
		TokenData data = User.getTokenData();
		 if( "TEACHER".equals(data.getRole())) {

//			userList = session.createNativeQuery("SELECT l.languagesId,la.name AS languageName, sua.name AS studentName,sua.startDate,sua.modifiedDate\r\n"
//					+ "FROM lessons l\r\n"
//					+ "LEFT JOIN courses c ON c.languagesId = l.languagesId\r\n"
//					+ "LEFT JOIN languages la ON la.languagesId = l.languagesId\r\n"
//					+ "LEFT JOIN useraccount sua ON sua.userAccountId = c.studentId\r\n"
//					+ "WHERE l.userAccountId =  :userId AND sua.status = 1\r\n"
////					+ "GROUP BY l.languagesId,c.studentId\r\n"
//					+ "").setParameter("userId", data.getUserId())
//					.getResultList();
		 userList = session.createNativeQuery(
			        "SELECT l.lessonsId, l.userAccountId AS lessUser,l.languagesId, la.name, sua.userAccountId, sua.name AS studentName, sua.age, sua.gender,\r\n"
			        + "sua.photo, sua.userName, sua.address, sua.nrc, sua.email, sua.phonenum, sua.degree, sua.file, e.examMark, \r\n"
			        + "sua.startDate, sua.modifiedDate, c.type \r\n"
			        + "FROM lessons l \r\n"
			        + "LEFT JOIN languages la ON la.languagesId = l.languagesId \r\n"
			        + "LEFT JOIN courses c ON c.languagesId = l.languagesId \r\n"
			        + "LEFT JOIN useraccount sua ON sua.userAccountId = c.studentId \r\n"
			        + "LEFT JOIN examans e ON e.userAccountId = c.studentId \r\n"
					+ "WHERE l.userAccountId =  :userId AND sua.status = 1\r\n"
			        + "GROUP BY l.languagesId, la.name, sua.userAccountId, sua.name, sua.age, sua.gender, sua.photo, sua.userName, sua.address, sua.nrc, \r\n"
			        + "sua.email, sua.phonenum, sua.degree, sua.file, e.examMark, sua.startDate, sua.modifiedDate\r\n"
			        + ""
			    ).setParameter("userId", data.getUserId()).getResultList();
		 
				for (Object[] obj : userList) {
				    UserAccountDto dto = new UserAccountDto();
				    
			        LessonsDto lessonsDto = new LessonsDto(((Number)obj[0]).intValue());
			        dto.setLessonsDto(lessonsDto);
			        UserAccountDto studentDto = new UserAccountDto(((Number)obj[1]).intValue());
			        dto.setStudentDto(studentDto);

				    // Language info
				    if (obj[2] != null) {
				        LanguagesDto langDto = new LanguagesDto(((Number)obj[2]).intValue(), (String)obj[3]);
				        dto.setLanguagesDto(langDto);
				    }

				    dto.setUserAccountId(obj[4] != null ? ((Number)obj[4]).intValue() : null);
				    dto.setName((String) obj[5]);
				    dto.setAge((Date) obj[6]);
				    dto.setGender((String) obj[7]);
				    dto.setPhoto((String) obj[8]);
				    dto.setUserName((String) obj[9]);
				    dto.setAddress((String) obj[10]);
				    dto.setNrc((String) obj[11]);
				    dto.setEmail((String) obj[12]);
				    dto.setPhonenum((String) obj[13]);
				    dto.setDegree((String) obj[14]);
				    dto.setFile((String) obj[15]);

				    if (obj[16] != null) {
				        ExamansDto examDto = new ExamansDto();
				        examDto.setExamMark(((Number)obj[16]).intValue());
				        dto.setExamDto(examDto);
				    }

				    dto.setStartDate((Date) obj[17]);
				    dto.setModifiedDate((Date) obj[18]);

				    CoursesDto coursesDto = new CoursesDto();
				    coursesDto.setType((String) obj[19]);
				    dto.setCoursesDto(coursesDto);

				    dtoList.add(dto);
				}
				if("TE".equals(userType)) {
					List<UserAccount> userListOne  = session.createQuery("SELECT ua FROM UserAccount ua "
							+ " Where ua.status=1 AND ua.userType='TEACHER' "
							+ " ORDER BY ua.name ASC ").getResultList();
					for(UserAccount ua:userListOne) {
						UserAccountDto dto = new UserAccountDto(ua);
						dtoList.add(dto);
					}
				}

		}
//	        for (Object[] obj : userList) {
//	            int languagesId = Integer.parseInt(obj[0].toString());
//	            String languageName = (String) obj[1];
//	            String studentName = (String) obj[2];
//	            Date startDate = (Date) obj[3];
//	            Date modifiedDate = (Date) obj[4];
//
//	            UserAccountDto dto = new UserAccountDto(studentName, startDate, modifiedDate);
//	            dto.setStudentDto(new UserAccountDto(studentName));
//	            dto.setLanguagesDto(new LanguagesDto(languagesId, languageName));
//
//	            dtoList.add(dto);
////	            return dtoList;
//	        }
//		}
		
	 	
		

		//List<UserAccountDto> allList1 = new ArrayList<UserAccountDto>();
			
//			int userId = User.getUserId();
//			String role = User.getUserRole();
			
//			if("ADMIN".equals(role))
		
			else if ("STU".equals(userType)) {
			    // Run native SQL and return a list of Object[]
			    List<Object[]> userList1 = session.createNativeQuery(
			        "SELECT l.languagesId, la.name, sua.userAccountId, sua.name AS studentName, sua.age, sua.gender,\r\n"
			        + "sua.photo, sua.userName, sua.address, sua.nrc, sua.email, sua.phonenum, sua.degree, sua.file, e.examMark, \r\n"
			        + "sua.startDate, sua.modifiedDate, c.type \r\n"
			        + "FROM lessons l \r\n"
			        + "LEFT JOIN languages la ON la.languagesId = l.languagesId \r\n"
			        + "LEFT JOIN courses c ON c.languagesId = l.languagesId \r\n"
			        + "LEFT JOIN useraccount sua ON sua.userAccountId = c.studentId \r\n"
			        + "LEFT JOIN examans e ON e.userAccountId = c.studentId \r\n"
			        + "WHERE sua.status = 1 \r\n"
			        + "GROUP BY l.languagesId, la.name, sua.userAccountId, sua.name, sua.age, sua.gender, sua.photo, sua.userName, sua.address, sua.nrc, \r\n"
			        + "sua.email, sua.phonenum, sua.degree, sua.file, e.examMark, sua.startDate, sua.modifiedDate\r\n"
			        + ""
			    ).getResultList();

			    // Loop through the rows and map them to DTOs
			    for (Object[] obj : userList1) {
			        UserAccountDto dto = new UserAccountDto();
			        dto.setUserAccountId(obj[2] != null ? ((Number) obj[2]).intValue() : null);
			        dto.setName((String) obj[3]);
			        dto.setAge((Date) obj[4]);
			        dto.setGender((String) obj[5]);
			        dto.setPhoto((String) obj[6]);
			        dto.setUserName((String) obj[7]);
			        dto.setAddress((String) obj[8]);
			        dto.setNrc((String) obj[9]);
			        dto.setEmail((String) obj[10]);
			        dto.setPhonenum((String) obj[11]);
			        dto.setDegree((String) obj[12]);
			        dto.setFile((String) obj[13]);

			        // Language info (obj[0] = languagesId, obj[1] = languageName)
			        if (obj[0] != null) {
			            LanguagesDto langDto = new LanguagesDto(((Number) obj[0]).intValue(), (String) obj[1]);
			            dto.setLanguagesDto(langDto);
			        }

			        // Exam mark info (obj[14] = examMark)
			        if (obj[14] != null) {
			            ExamansDto examDto = new ExamansDto();
			            examDto.setExamMark(((Number) obj[14]).intValue());
			            dto.setExamDto(examDto);
			        }

			        dto.setStartDate((Date) obj[15]);
			        dto.setModifiedDate((Date) obj[16]);
			        
			        CoursesDto coursesDto = new CoursesDto();
			        coursesDto.setType((String) obj[17]);
			        dto.setCoursesDto(coursesDto);
			        
			        dtoList.add(dto);
			    }
			}else if ("TEACHER".equals(userType)) {
				List <Object[]> userListOne = session.createNativeQuery("SELECT u.userAccountId,l.languagesId,l.name AS languageName,u.name AS TeacherName,u.gender,u.nrc,\r\n"
						+ "u.email,u.phonenum,u.address,u.photo,u.degree,u.file,u.startDate\r\n"
						+ "FROM useraccount u\r\n"
						+ "LEFT JOIN lessons le ON u.userAccountId = le.userAccountId\r\n"
						+ "LEFT JOIN languages l ON  l.languagesId = le.languagesId\r\n"
						+ "WHERE u.status = 1 AND u.userType = \"TEACHER\"\r\n"
						+ "GROUP BY u.userAccountId").getResultList();
				for (Object[] obj : userListOne) {
				    int userAccountId = ((Number) obj[0]).intValue();

				    Integer languagesId = obj[1] != null ? ((Number) obj[1]).intValue() : null;

				    String languageName = obj[2] != null ? (String) obj[2] : null;
				    String teacherName = obj[3] != null ? (String) obj[3] : null;
				    String gender = obj[4] != null ? (String) obj[4] : null;
				    String nrc = obj[5] != null ? (String) obj[5] : null;           
				    String email = obj[6] != null ? (String) obj[6] : null;
				    String phonenum = obj[7] != null ? (String) obj[7] : null;
				    String address = obj[8] != null ? (String) obj[8] : null;
				    String photo = obj[9] != null ? (String) obj[9] : null;
				    String degree = obj[10] != null ? (String) obj[10] : null;
				    String file = obj[11] != null ? (String) obj[11] : null;
				    Date startDate = obj[12] != null ? (Date) obj[12] : null;

				    UserAccountDto dto = new UserAccountDto(teacherName, gender, nrc, email, phonenum, address, photo, degree, file, startDate);

				    if (languagesId != null) {
				        dto.setLanguagesDto(new LanguagesDto(languagesId, languageName));
				    }

				    dtoList.add(dto);
				}

					//return
				
			}
				else if("ALL".equals(userType)) {
						
					List<UserAccount> userListOne = session.createQuery("SELECT ua FROM UserAccount ua where ua.status=1 ORDER BY ua.name ASC ")
							.getResultList();
					for(UserAccount ua:userListOne) {
						UserAccountDto dto = new UserAccountDto(ua);
						dtoList.add(dto);
					}
						//return dtoList;
					}
				
				else {
					List<UserAccount> userListOne  = session.createQuery("SELECT ua FROM UserAccount ua "
							+ " Where ua.status=1 AND ua.userType=:userType "
							+ " ORDER BY ua.name ASC ").setParameter("userType", userType).getResultList();
					for(UserAccount ua:userListOne) {
						UserAccountDto dto = new UserAccountDto(ua);
						dtoList.add(dto);
					}
						//return dtoList;
				}
			return dtoList;
					
	}

	@Override
	public void saveUserAccounts(UserAccount user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public void updateUserAccount(UserAccount user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public void deleteUserAccounts(int userAccountId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.delete(new UserAccount(userAccountId));
	}

	@Override
	public UserAccount getLoginAccount(String userName, String password) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String encryPassword = Cryption.encryption(password);
		List<UserAccount> list = session.createQuery("SELECT ac FROM UserAccount ac WHERE  ac.userName=:userName  AND ac.encryptPassword =:encryPassword AND ac.status = 1  ")
				.setParameter("userName", userName)
				.setParameter("encryPassword", encryPassword)
				.getResultList();
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

    @PersistenceContext
    private EntityManager entityManager;


	@Override
	public Optional<UserAccount> findById(int userAccountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public void save(UserAccount user) {
        if (user.getUserAccountId() == 0) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }


}