package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Examans;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.UserAccountDto;

public interface UserAccountDao {
	UserAccount getUserAccountsById(int userId);

	List<UserAccountDto> getUserAccounts(String userType);

	void saveUserAccounts(UserAccount user);

	void updateUserAccount(UserAccount user);

	void deleteUserAccounts(int userAccountId);

	UserAccount getLoginAccount(String userName, String password);

}
