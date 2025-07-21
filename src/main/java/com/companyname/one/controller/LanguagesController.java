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

import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.service.LanguagesService;


@RestController
@RequestMapping("/api/v1/")
public class LanguagesController {
	@Autowired
	
	LanguagesService lanService;
	
	@GetMapping("languages")
	public List<LanguagesDto> getLanguages(){
		return lanService.getLanguages();
	}
	
	@PostMapping("languages")
	public LanguagesDto addLanguages(@RequestBody LanguagesDto dto) {
		try 
		{
		    System.out.println("Received Language DTO: " + dto);
			return lanService.addLanguages(dto);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Add,Languages is Error!", e);
		}
	}
	
	@PutMapping("languages/{languagesId}")
	public LanguagesDto updateLanguage(@PathVariable("languagesId")int languagesId,@RequestBody LanguagesDto dto) {
		try {
			
            dto.setLanguagesId(languagesId);
			return lanService.updateLanguage(dto);	
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Update,Language Error!", e);
		}
	}
	
	@DeleteMapping("languages/{languagesId}")
	public int deleteLanguage(@PathVariable("languagesId")int languagesId) {
		try {
			return lanService.deleteLanguage(languagesId);
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
	
}
