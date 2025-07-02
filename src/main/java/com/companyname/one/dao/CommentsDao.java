package com.companyname.one.dao;

import java.util.List;

import com.companyname.one.domain.Comments;
import com.companyname.one.dto.CommentsDto;

public interface CommentsDao {

	List<CommentsDto> getComments();

	void addComments(Comments comments);

	void updateComments(Comments comments);

}
