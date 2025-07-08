package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.service.UserAccountService;

@Controller
public class IndexController {
	@Autowired
	UserAccountService userService;
	@GetMapping(value = {"/","/*"})
	String login() {
		return "index";
	}
	@GetMapping(value = {"/two"})
	String osData(Model model) {
		List<UserAccountDto> neworder= userService.getUserAccounts("ALL");
		model.addAttribute("orderlist",neworder);
		return "tracking";	
	}
}
