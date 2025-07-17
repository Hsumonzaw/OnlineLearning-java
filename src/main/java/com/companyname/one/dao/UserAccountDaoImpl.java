package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.LanguagesDto;
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
		TokenData data = User.getTokenData();
		data.getRole();

		List<UserAccountDto> dtoList = new ArrayList<UserAccountDto>();
		List<UserAccountDto> allList1 = new ArrayList<UserAccountDto>();
			
			int userId = User.getUserId();
			String role = User.getUserRole();
			
//			if("ADMIN".equals(role))
		 	if("TEACHER".equals(userType)) {

			userList = session.createNativeQuery("SELECT l.languagesId,la.name AS languageName, sua.name AS studentName,sua.startDate,sua.modifiedDate\r\n"
					+ "FROM lessons l\r\n"
					+ "LEFT JOIN courses c ON c.languagesId = l.languagesId\r\n"
					+ "LEFT JOIN languages la ON la.languagesId = l.languagesId\r\n"
					+ "LEFT JOIN useraccount sua ON sua.userAccountId = c.studentId\r\n"
					+ "WHERE l.userAccountId =  :userId\r\n"
					+ "GROUP BY l.languagesId,c.studentId\r\n"
					+ "").setParameter("userId", userId)
					.getResultList();
			
	        for (Object[] obj : userList) {
	            int languagesId = Integer.parseInt(obj[0].toString());
	            String languageName = (String) obj[1];
	            String studentName = (String) obj[2];
	            Date startDate = (Date) obj[3];
	            Date modifiedDate = (Date) obj[4];

	            UserAccountDto dto = new UserAccountDto(studentName, startDate, modifiedDate);
	            dto.setStudentDto(new UserAccountDto(studentName));
	            dto.setLanguagesDto(new LanguagesDto(languagesId, languageName));

	            dtoList.add(dto);
	        }
		}else if("ALL".equals(userType)) {
				
				allList1 = session.createQuery("SELECT ua FROM UserAccount ua where ua.status=1 ORDER BY ua.name ASC ").getResultList();
				return allList1;
			}
		else {
			allList1 = session.createQuery("SELECT ua FROM UserAccount ua "
					+ " Where ua.status=1 AND ua.userType=:userType "
					+ " ORDER BY ua.name ASC ").setParameter("userType", userType).getResultList();
			return allList1;
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

}
