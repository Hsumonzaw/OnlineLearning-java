package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.companyname.one.dto.CoursesDto;
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
	public List<LessonsDto> getLessons(@RequestParam(value = "freeVideo", defaultValue = "ALL") String freeVideo,
			@RequestParam(value = "languageId", defaultValue = "0") int languageId){
		try {
			return lessService.getLessons(freeVideo,languageId);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("get,Lessons Error!", e);
		}
		
	}
	@GetMapping("free/lessons")
	public List<LessonsDto> getLessons(@RequestParam(value = "languageId", defaultValue = "0") int languageId){
		try {
			return lessService.getLessons("FREE",languageId);
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
			e.printStackTrace();

			throw new RuntimeException("Add,Lessons is Error!", e);
		}
	}
	@PutMapping("lessons/{lessonsId}")
	public LessonsDto updateLessons(@PathVariable("lessonsId")int lessonsId ,
			@RequestBody LessonsDto dto) {
		try {
		
            
            dto.setLessonsId(lessonsId);
			return lessService.updateLessons(dto);	
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			throw new RuntimeException("Update,Course Error!", e);
		}
	}
	@DeleteMapping("lessons/{lessonsId}")
	public int deleteLessons(@PathVariable("lessonsId")int lessonsId) {
		try {
			return lessService.deleteLessons(lessonsId);
		}catch (Exception e) {
			e.printStackTrace();

			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
	
	@PutMapping("lessons/{lessonsId}/pdf")
	public int updateFile(@PathVariable("lessonsId")int lessonsId,@RequestParam(value = "file",required=false) MultipartFile file){
		try {
			return lessService.updateFile(lessonsId,file);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("SAVE,News Error!", e);
		}
		
	}

}
