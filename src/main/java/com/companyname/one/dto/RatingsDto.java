package com.companyname.one.dto;

import java.util.Date;

import com.companyname.one.util.DateFormatDeserializer;
import com.companyname.one.util.DateFormatSerializer;
import com.companyname.one.util.DateTimeFormatDeserializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(value = Include.USE_DEFAULTS)
@NoArgsConstructor
@AllArgsConstructor
public class RatingsDto {
  public RatingsDto(int ratingsId,int rate,Date date, Date modifiedDate) {
    this.ratingsId = ratingsId;
    this.rate = rate;
    this.date = date;
    this.modifiedDate = modifiedDate;
  }

  private int ratingsId;
  private UserAccountDto userAccountDto;
  private LessonsDto lessonsDto;
  private int rate;
  @JsonSerialize(using = DateFormatSerializer.class)
  @JsonDeserialize(using = DateFormatDeserializer.class)
  private Date date;
  @JsonSerialize(using = DateFormatSerializer.class)
  @JsonDeserialize(using = DateFormatDeserializer.class)
  private Date modifiedDate;
  
}