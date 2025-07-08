package com.companyname.one.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.UserAccount;
import com.companyname.one.util.Cryption;
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
	public List<UserAccount> getUserAccounts(String userType) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<UserAccount> userList = null;
		if("ALL".equals(userType)) {
			userList = session.createQuery("SELECT ua FROM UserAccount ua where ua.status=1 ORDER BY ua.name ASC ").getResultList();
		}else {
			userList = session.createQuery("SELECT ua FROM UserAccount ua "
					+ " Where ua.status=1 AND ua.userType=:userType "
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
