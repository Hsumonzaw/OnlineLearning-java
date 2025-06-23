package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.service.UserAccountService;

@RestController
@RequestMapping("/api/v1/")
public class UserAccountController {
@Autowired
UserAccountService userService;
	@GetMapping("useraccounts")
	public List<UserAccountDto> getUserAccounts(@RequestParam(value = "userType",defaultValue = "ALL") String userType){
		try {
			return userService.getUserAccounts(userType);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("get,News Error!", e);
		}
		
	}
	@PostMapping("useraccounts")
	public int saveUserAccounts(@RequestBody UserAccountDto dto){
		try {
			dto.setPassword(dto.getPassword().toLowerCase().toString());
			return userService.saveUserAccounts(dto);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("SAVE,News Error!", e);
		}
		
	}
}
