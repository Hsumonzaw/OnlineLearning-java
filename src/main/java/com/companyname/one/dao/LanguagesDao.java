package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Languages;
import com.companyname.one.dto.LanguagesDto;

public interface LanguagesDao {

	List<Languages> getLanguages();

	void addLanguages(Languages languages);

	void updateLanguage(Languages languages);

	void deleteLanguage(int languagesId);

	

}
