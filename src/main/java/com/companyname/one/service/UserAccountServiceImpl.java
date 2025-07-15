package com.companyname.one.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dao.UserAccountDao;
import com.companyname.one.domain.Lessons;
import com.companyname.one.domain.UserAccount;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.util.ConvertDate;
import com.companyname.one.util.Cryption;
import com.companyname.one.util.User;
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

	@Transactional(readOnly=false)
	@Override
	public int updateUserAccounts(UserAccountDto dto) {
		// TODO Auto-generated method stub
		
		UserAccount user = userDao.getUserAccountsById(dto.getUserAccountId());
		user.setTeacherId(dto.getTeacherId());
		user.setName(dto.getName());
		user.setAge(dto.getAge());
		user.setStatus(1);
		user.setUserType(dto.getUserType());
		user.setAddress(dto.getAddress());
		user.setNrc(dto.getNrc());
		user.setEmail(dto.getEmail());
		user.setPhonenum(dto.getPhonenum());
		user.setDegree(dto.getDegree());
		user.setFile(dto.getFile());
		user.setStartDate(dto.getStartDate());
		user.setModifiedDate(new Date());
		user.setUserName(dto.getUserName());
		user.setPassword(passEncoder.encode(dto.getPassword().toString().trim()));
		user.setEncryptPassword(Cryption.encryption(dto.getPassword()));
		user.setCreateId(User.getUserId());
//		UserAccount user = new UserAccount(dto);
//		user.setPassword(passEncoder.encode(dto.getPassword().toString().trim()));
//		userDao.updateUserAccount(user);
		return dto.getUserAccountId();
	}

	@Transactional(readOnly=false)
	@Override
	public int deleteUserAccounts(int userAccountId) {
		// TODO Auto-usergenerated method stub
		UserAccount user = userDao.getUserAccountsById(userAccountId);
		user.setStatus(0);
		//userDao.deleteUserAccounts(userAccountId);
		return userAccountId;
	}

	@Transactional(readOnly=false)
	@Override
	public int updatePhoto(int userAccountId, MultipartFile file) {
		// TODO Auto-generated method stub
		String oldPhoto = "";
		UserAccount ua = userDao.getUserAccountsById(userAccountId);
		oldPhoto = ua.getPhoto();
		String photo = ConvertDate.convertyymmddhhmmss(new Date());
		ua.setPhoto(photo);
		
		String pwd=new File("").getAbsolutePath();
		if(oldPhoto!=null) {
			File deleteFile=new File(pwd+"/userphoto/"+oldPhoto+".png");
			deleteFile.delete();
		}
		
		
		File dir=new File(pwd+"/userphoto/");
		String outPath=pwd+"/userphoto/"+photo+".png";
		File dest=new File(outPath);
		try {
			if (!dir.exists()) {
				dir.mkdir();
			}
			file.transferTo(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return userAccountId;
	}
	
	@Transactional(readOnly=false)
	@Override
	public int updateFile(int userAccountId, MultipartFile file) {
		// TODO Auto-generated method stub
		String oldFile = "";
		UserAccount ua = userDao.getUserAccountsById(userAccountId);
		oldFile = ua.getFile();
		String file1 = ConvertDate.convertyymmddhhmmss(new Date());
		ua.setFile(file1);
		
		String pwd=new File("").getAbsolutePath();
		if(oldFile!=null) {
			File deleteFile=new File(pwd+"/userfile/"+oldFile+".pdf");
			deleteFile.delete();
		}
		
		
		File dir=new File(pwd+"/userfile/");
		String outPath=pwd+"/userfile/"+file+".pdf";
		File dest=new File(outPath);
		try {
			if (!dir.exists()) {
				dir.mkdir();
			}
			file1.transferTo(dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return userAccountId;

	}

}
