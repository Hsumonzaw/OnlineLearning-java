package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Courses;
import com.companyname.one.dto.CoursesDto;

public interface CoursesDao {

	List<CoursesDto> getCourses();

	void addCourses(Courses c);

	void updateCourse(Courses c);

	void deleteCourse(int coursesId);

}
