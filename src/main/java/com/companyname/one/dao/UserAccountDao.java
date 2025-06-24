package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.UserAccount;

public interface UserAccountDao {
	UserAccount getUserAccountsById(int userId);

	List<UserAccount> getUserAccounts(String userType);

	void saveUserAccounts(UserAccount user);

	void updateUserAccount(UserAccount user);

	void deleteUserAccounts(int userAccountId);
}
