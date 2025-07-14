package com.companyname.one.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dto.CoursesDto;

public interface CoursesService {

	List<CoursesDto> getCourses();

	CoursesDto addCourses(CoursesDto dto);

	CoursesDto updateCourse(CoursesDto dto);

	int deleteCourse(int coursesId);

	int updatePhoto(int coursesId, MultipartFile file);

}
