package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.one.dto.LanguagesDto;
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
	@PostMapping("lessons")
	public LessonsDto addLessons(@RequestBody LessonsDto dto) {
		try 
		{
			return lessService.addLessons(dto);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Add,Lessons is Error!", e);
		}
	}
}
