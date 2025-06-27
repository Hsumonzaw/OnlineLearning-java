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
import org.springframework.web.bind.annotation.RestController;

import com.companyname.one.dto.CoursesDto;

import com.companyname.one.service.CoursesService;

@RestController
@RequestMapping("/api/v1/")
public class CoursesController {
	@Autowired
	
	CoursesService courService;
	
	@GetMapping("courses")
	public List<CoursesDto> getCourses(){
		return courService.getCourses();
	}
	
	@PostMapping("courses")
	public CoursesDto addCourses(@RequestBody CoursesDto dto) {
		try 
		{
			return courService.addCourses(dto);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Add,Courses is Error!", e);
		}
	}
	
	@PutMapping("courses/{coursesId}")
	public CoursesDto updateCourse(@PathVariable("coursesId")int coursesId,
			@RequestBody CoursesDto dto) {
		try {
			
            
            dto.setCoursesId(coursesId);
			return courService.updateCourse(dto);	
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Update,Course Error!", e);
		}
	}
	
	@DeleteMapping("courses/{coursesId}")
	public int deleteCourse(@PathVariable("coursesId")int coursesId) {
		try {
			return courService.deleteCourse(coursesId);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
}
