package com.companyname.one.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.security.CustomUserDetailsService;
import com.companyname.one.security.LoginDto;
import com.companyname.one.service.UserAccountService;
@RestController
@RequestMapping("/api/v1/")
//@RequestMapping("/api/v1/user")
public class UserAccountController<ChangePasswordRequest> {
@Autowired
UserAccountService userService;
@Autowired 
CustomUserDetailsService customUserDetailService;
	
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
	@GetMapping("free/useraccounts")
	public List<UserAccountDto> getUserAccountsFree(@RequestParam(value = "userType",defaultValue = "ALL") String userType){
		try {
			return userService.getUserAccounts(userType);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("get,News Error!", e);
		}
		
	}
	@GetMapping("free/teachers")
	public List<UserAccountDto> getTeachers() {
	    return userService.getUserAccounts("TE");
	}
//	@GetMapping("/studentdetail")
//	public List<UserAccountDto> getStudentDetail() {
//	    return userService.getUserAccounts("STUDENT");
//	}
//	@GetMapping("/teachercourse")
//	public List<UserAccountDto> getTeacherCourse() {
//	    return userService.getUserAccounts("TEACHER");
//	}




	@PostMapping("free/useraccounts")
	public int saveFreeUserAccounts(@RequestBody UserAccountDto dto){
		try {
			dto.setPassword(dto.getPassword().toLowerCase().toString());
			return userService.saveUserAccounts(dto);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("SAVE,News Error!", e);
		}
		
	}
//	@PostMapping("free/useraccounts")
//	public int saveUserAccountsFree(@RequestBody UserAccountDto dto){
//		try {
//			dto.setPassword(dto.getPassword().toLowerCase().toString());
//			return userService.saveUserAccounts(dto);
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			throw new RuntimeException("SAVE,News Error!", e);
//		}
//		
//	}
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
	@PutMapping("useraccounts/{userAccountId}")
	public int updateUserAccounts(@PathVariable("userAccountId")int userAccountId, @RequestBody UserAccountDto dto){
		try {
			dto.setUserAccountId(userAccountId);
			dto.setPassword(dto.getPassword().toLowerCase().toString());
			return userService.updateUserAccounts(dto);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Update,News Error!", e);
		}
		
	}
	@DeleteMapping("useraccounts/{userAccountId}")
	public int deleteUserAccounts(@PathVariable("userAccountId")int userAccountId){
		try {
			return userService.deleteUserAccounts(userAccountId);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Delete,News Error!", e);
		}
		
	}
	@PutMapping("useraccounts/{userAccountId}/photo")
	public int updatePhoto(@PathVariable("userAccountId")int userAccountId,@RequestParam(value = "file",required=false) MultipartFile file){
		try {
			return userService.updatePhoto(userAccountId,file);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("SAVE,News Error!", e);
		}
		
	}
	@PutMapping("useraccounts/{userAccountId}/file")
	public int updateFile(@PathVariable("userAccountId")int userAccountId,@RequestParam(value = "file",required=false) MultipartFile file){
		try {
			return userService.updateFile(userAccountId,file);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("SAVE,News Error!", e);
		}
		
	}
	@GetMapping("useraccounts/login")
	public LoginDto login(@RequestParam("userName")String userName,@RequestParam("password")String password) {

		try {
				LoginDto loginDto = new LoginDto();
				loginDto.setUserName(userName.trim());
				loginDto.setPassword(password.toLowerCase().trim());
				LoginDto login = customUserDetailService.loginAccount(loginDto);
				return login;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("UserName and Password is wrong!", e);
		}
	}
//	@PutMapping("users/{userAccountId}/changepassword")
//	public ResponseEntity<String> changePassword(
//	        @PathVariable int userAccountId,
//	        @RequestParam String oldPassword,
//	        @RequestParam String newPassword) {
//	    try {
//	        boolean changed = userService.changePassword(userAccountId, oldPassword, newPassword);
//	        if (changed) {
//	            return ResponseEntity.ok("Password changed successfully");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect or user not found");
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .body("Error changing password: " + e.getMessage());
//	    }
	
	@PutMapping("useraccounts/{userAccountId}/changepassword")
	public ResponseEntity<String> changePassword(
	    @PathVariable int userAccountId,
	    @RequestParam String oldPassword,
	    @RequestParam String newPassword) {
	    try {
	        boolean changed = userService.changePassword(userAccountId, oldPassword, newPassword);
	        if (changed) {
	            return ResponseEntity.ok("Password changed successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Old password is incorrect or user not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error changing password: " + e.getMessage());
	    }
	}

	}
//	 @PostMapping("/changepassword") // The specific endpoint for changing the password
//	    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
//	        // ... logic to change password
//	        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
//	    }

