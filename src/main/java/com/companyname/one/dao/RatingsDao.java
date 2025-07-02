package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Ratings;
import com.companyname.one.dto.RatingsDto;

public interface RatingsDao {

  List<RatingsDto> getRate();

  void addRate(Ratings rate);

  void updateRate(Ratings rate);
  

}