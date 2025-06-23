package com.companyname.one.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.UserAccountDao;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.UserAccountDto;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	@Autowired
	UserAccountDao userDao;
	@Autowired
	PasswordEncoder passEncoder;
	@Transactional(readOnly=true)
	@Override
	public List<UserAccountDto> getUserAccounts(String userType) {
		// TODO Auto-generated method stub
		List<UserAccount> userList = userDao.getUserAccounts(userType);
		List<UserAccountDto> dtoList = new ArrayList<>();
		for(UserAccount user:userList) {
			UserAccountDto dto = new UserAccountDto(user);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Transactional(readOnly=false)
	@Override
	public int saveUserAccounts(UserAccountDto dto) {
		// TODO Auto-generated method stub
		UserAccount user = new UserAccount(dto);
		user.setPassword(passEncoder.encode(dto.getPassword().toString().trim()));
		userDao.saveUserAccounts(user);
		return user.getUserAccountId();
	}

}
