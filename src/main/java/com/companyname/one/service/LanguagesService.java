package com.companyname.one.service;

import java.util.List;

import com.companyname.one.dto.LanguagesDto;

public interface LanguagesService {

	List<LanguagesDto> getLanguages();

	LanguagesDto addLanguages(LanguagesDto dto);

	LanguagesDto updateLanguage(LanguagesDto dto);

	int deleteLanguage(int languagesId);

}
