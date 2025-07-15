package com.companyname.one.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dto.UserAccountDto;

public interface UserAccountService {

	List<UserAccountDto> getUserAccounts(String userType);

	int saveUserAccounts(UserAccountDto dto);

	int updateUserAccounts(UserAccountDto dto);

	int deleteUserAccounts(int userAccountId);

	int updatePhoto(int userAccountId, MultipartFile file);

	int updateFile(int userAccountId, MultipartFile file);

}
