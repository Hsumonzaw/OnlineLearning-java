package com.companyname.one.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.companyname.one.dao.CommentsDao;
import com.companyname.one.dao.RatingsDao;
import com.companyname.one.domain.Comments;
import com.companyname.one.domain.Ratings;
import com.companyname.one.dto.CommentsDto;
import com.companyname.one.dto.RatingsDto;
import com.companyname.one.util.User;
@Service
public class CommentsServiceImpl implements CommentsService {
	@Autowired
	  CommentsDao commentsDao;
	  @Transactional(readOnly=true)
	@Override
	public List<CommentsDto> getComments() {
		// TODO Auto-generated method stub
	    return commentsDao.getComments();
	}
	  @Transactional(readOnly=false)
	@Override
	public CommentsDto addComments(CommentsDto dto) {
		// TODO Auto-generated method stub
		Comments comments = new Comments();
		comments.setUserAccountId(User.getUserId());
		comments.setLessonsId(dto.getLessonsDto().getLessonsId());
		comments.setMessage(dto.getMessage());
		comments.setDate(new Date());
		comments.setModifieddate(new Date());
		commentsDao.addComments(comments);

	       
	        return dto;
	 }
	 @Transactional(readOnly=false)
	@Override
	public CommentsDto updateComments(CommentsDto dto) {
		// TODO Auto-generated method stub
		 Comments comments = new Comments();
		 comments.setCommentsId(dto.getCommentsId());
			comments.setUserAccountId(User.getUserId());
			comments.setLessonsId(dto.getLessonsDto().getLessonsId());
			comments.setMessage(dto.getMessage());
			comments.setDate(new Date());
			comments.setModifieddate(new Date());
			commentsDao.updateComments(comments);

		
		        return dto;
	        }

}
