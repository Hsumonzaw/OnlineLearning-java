package com.companyname.one.service;

import java.util.List;

import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.RatingsDto;


public interface RatingsService {

  List<RatingsDto> getRate();

  RatingsDto addRate(RatingsDto dto);

  RatingsDto updateRate(RatingsDto dto);

}