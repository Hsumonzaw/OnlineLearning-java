package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
import com.companyname.one.service.LessonsService;

@RestController
@RequestMapping("/api/v1/")
public class LessonsController {
	@Autowired
	LessonsService lessService;
	@GetMapping("lessons")
	public List<LessonsDto> getLessons(@RequestParam(value = "freeVideo",defaultValue = "FREE") String freeVideo){
		try {
			return lessService.getLessons(freeVideo);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("get,Lessons Error!", e);
		}
		
	}
}
