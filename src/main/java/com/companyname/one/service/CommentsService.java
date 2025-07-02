package com.companyname.one.service;

import java.util.List;

import com.companyname.one.dto.CommentsDto;
import com.companyname.one.dto.RatingsDto;

public interface CommentsService {

	List<CommentsDto> getComments();

	CommentsDto addComments(CommentsDto dto);

	CommentsDto updateComments(CommentsDto dto);

}
