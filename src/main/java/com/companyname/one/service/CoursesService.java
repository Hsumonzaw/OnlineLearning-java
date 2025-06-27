package com.companyname.one.service;

import java.util.List;

import com.companyname.one.dto.CoursesDto;

public interface CoursesService {

	List<CoursesDto> getCourses();

	CoursesDto addCourses(CoursesDto dto);

	CoursesDto updateCourse(CoursesDto dto);

	int deleteCourse(int coursesId);

}
