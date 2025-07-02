package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.RatingsDto;
import com.companyname.one.service.RatingsService;

@RestController
@RequestMapping("/api/v1/")
public class RatingsController {
@Autowired
  
  RatingsService rateService;
  
  @GetMapping("ratings")
  public List<RatingsDto> getRate(){
    return rateService.getRate();
  }
  @PostMapping("ratings")
  public RatingsDto addCourses(@RequestBody RatingsDto dto) {
    try 
    {
      return rateService.addRate(dto);
    }catch (Exception e) {
      // TODO: handle exception
      throw new RuntimeException("Add,Rating is Error!", e);
    }
  }
  

  @PutMapping("ratings/{ratingsId}")
  public RatingsDto updateRate(@PathVariable("ratingsId")int ratingsId,
      @RequestBody RatingsDto dto) {
    try {
            dto.setRatingsId(ratingsId);
      return rateService.updateRate(dto);  
      
    }catch (Exception e) {
      // TODO: handle exception
      throw new RuntimeException("Update,Course Error!", e);
    }
  }

  
}