package com.companyname.one.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dto.LessonsDto;

public interface LessonsService {

	List<LessonsDto> getLessons(String freeVideo,int languageId);

	LessonsDto addLessons(LessonsDto dto);

	LessonsDto updateLessons(LessonsDto dto);

	int deleteLessons(int lessonsId);

	int updateFile(int lessonsId, MultipartFile file);
	

}
