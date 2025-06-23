package com.companyname.one.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.UserAccount;
@Repository
public class UserAccountDaoImpl implements UserAccountDao{
	@Autowired
	SessionFactory sessionFactory;
	@Override
	public UserAccount getUserAccountsById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccount> getUserAccounts(String userType) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<UserAccount> userList = null;
		if("ALL".equals(userType)) {
			userList = session.createQuery("SELECT ua FROM UserAccount ua ORDER BY ua.name ASC ").getResultList();
		}else {
			userList = session.createQuery("SELECT ua FROM UserAccount ua "
					+ " Where ua.userType=:userType "
					+ " ORDER BY ua.name ASC ").setParameter("userType", userType).getResultList();
		}
		return userList;
	}

	@Override
	public void saveUserAccounts(UserAccount user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

}
