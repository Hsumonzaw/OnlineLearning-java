package com.companyname.one.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.RatingsDao;
import com.companyname.one.domain.Ratings;
import com.companyname.one.dto.RatingsDto;
import com.companyname.one.util.User;
@Service
public class RatingsServiceImpl implements RatingsService {
  @Autowired
  RatingsDao rateDao;
  @Transactional(readOnly=true)
  @Override
  public List<RatingsDto> getRate() {
    return rateDao.getRate();
  }
  
  @Transactional(readOnly=false)
  @Override
  public RatingsDto addRate(RatingsDto dto) {
    Ratings rate = new Ratings();
    rate.setUserAccountId(User.getUserId());
    rate.setLessonsId(dto.getLessonsDto().getLessonsId());
    rate.setRate(dto.getRate());
    rate.setDate(new Date());
    rate.setModifieddate(new Date());
        rateDao.addRate(rate);

       
        return dto;
  }

  @Transactional(readOnly=false)
  @Override
  public RatingsDto updateRate(RatingsDto dto) {
    // TODO Auto-generated method stub
    Ratings rate = new Ratings();
    rate.setRatingsId(dto.getRatingsId());
    rate.setUserAccountId(User.getUserId());
    rate.setLessonsId(dto.getLessonsDto().getLessonsId());
    rate.setRate(dto.getRate());
    rate.setDate(dto.getDate());
    rate.setModifieddate(new Date());
        rateDao.updateRate(rate);
        return dto;
  }

}