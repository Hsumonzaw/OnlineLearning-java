package com.companyname.one.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ratings")
public class Ratings {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)


  private int ratingsId;

  private Integer userAccountId;

  private Integer lessonsId;

  private int rate;

  private Date date;

  private Date modifieddate;


  
  public Ratings() {
    super();
    // TODO Auto-generated constructor stub
  }


  public int getRatingsId() {
    return ratingsId;
  }


  public void setRatingsId(int ratingsId) {
    this.ratingsId = ratingsId;
  }


  public Integer getUserAccountId() {
    return userAccountId;
  }

  public void setUserAccountId(Integer userAccountId) {
    this.userAccountId = userAccountId;
  }


  public Integer getLessonsId() {
    return lessonsId;
  }


  public void setLessonsId(Integer lessonsId) {
    this.lessonsId = lessonsId;
  }


  public int getRate() {
    return rate;
  }


  public void setRate(int rate) {
    this.rate = rate;
  }


  public Date getDate() {
    return date;
  }


  public void setDate(Date date) {
    this.date = date;
  }

  public Date getModifieddate() {
    return modifieddate;
  }


  public void setModifieddate(Date modifieddate) {
    this.modifieddate = modifieddate;
  }
  
}