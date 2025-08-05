package com.companyname.one.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.companyname.one.dto.LanguagesDto;

public interface LanguagesService {

	List<LanguagesDto> getLanguages(int index);

	LanguagesDto addLanguages(LanguagesDto dto);

	LanguagesDto updateLanguage(LanguagesDto dto);

	int deleteLanguage(int languagesId);

	int updateLanPhoto(int languagesId, MultipartFile file);

	int updateLanFile(int languagesId, MultipartFile file);

}
