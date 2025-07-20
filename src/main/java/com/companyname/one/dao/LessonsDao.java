package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Lessons;

public interface LessonsDao {

	List<Object[]> getLessons(String freeVideo,int languageId);

	 void addLessons(Lessons lessons);

	void updateLessons(Lessons less);

	void deleteLessons(int lessonsId);

	Lessons getLessonsId(int lessonsId);

}
