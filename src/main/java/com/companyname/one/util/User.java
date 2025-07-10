package com.companyname.one.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.companyname.one.security.TokenData;

public class User {
	    public static TokenData getTokenData(){
	    	TokenData data = (TokenData)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      return data;
	    }
	    public static int getUserId(){
//	    	TokenData data = (TokenData)SecurityContextHolder.getContext().getAuthentication().getDetails();
	      return Integer.parseInt(getTokenData().getUserId());
	    }
}
