package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.Ratings;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.LanguagesDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.RatingsDto;
import com.companyname.one.dto.UserAccountDto;
@Repository
public class RatingsDaoImpl implements RatingsDao {
  @Autowired
  SessionFactory sessionFactory;


  @Override
  public List<RatingsDto> getRate() {
    Session session = sessionFactory.getCurrentSession();
    List<Object[]> objList = session.createNativeQuery("SELECT r.ratingsId, us.name, l.lessonsId, r.rate, r.date, r.modifiedDate FROM ratings r \r\n"
        + "LEFT JOIN useraccount us ON r.userAccountId = us.userAccountId\r\n"
        + "LEFT JOIN lessons l ON r.lessonsId = l.lessonsId").getResultList();
    List <RatingsDto> dtoList = new ArrayList<RatingsDto>();
    for(Object[] obj:objList) {
      int ratingsId = Integer.parseInt(obj[0].toString());
      
      String name = (String)obj[1];
      int lessonsId = Integer.parseInt(obj[2].toString());
      int rate = Integer.parseInt(obj[3].toString());
      Date date = (Date)(obj[4]);
      Date modifiedDate = (Date)(obj[5]);

      RatingsDto dto = new RatingsDto(ratingsId,rate,date,modifiedDate);
      
      dto.setUserAccountDto(new UserAccountDto(name));
      
      dto.setLessonsDto(new LessonsDto(lessonsId));
      
      dtoList.add(dto);
    }
    return dtoList;
  }


  @Override
  public void addRate(Ratings rate) {
    Session session = sessionFactory.getCurrentSession();
    session.save(rate);    
  }


  @Override
  public void updateRate(Ratings rate) {
    // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    session.update(rate);  
    
  }

}