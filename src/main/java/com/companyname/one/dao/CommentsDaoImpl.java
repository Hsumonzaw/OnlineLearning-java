package com.companyname.one.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.companyname.one.domain.Comments;
import com.companyname.one.domain.Ratings;
import com.companyname.one.dto.CommentsDto;
import com.companyname.one.dto.LessonsDto;
import com.companyname.one.dto.UserAccountDto;
@Repository
public class CommentsDaoImpl implements CommentsDao {
	 @Autowired
	  SessionFactory sessionFactory;

	@Override
	public List<CommentsDto> getComments() {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		    List<Object[]> objList = session.createNativeQuery("SELECT c.commentsId, us.name, l.lessonsId, c.message, c.date, c.modifiedDate FROM comments c \r\n"
		        + "LEFT JOIN useraccount us ON c.userAccountId = us.userAccountId\r\n"
		        + "LEFT JOIN lessons l ON c.lessonsId = l.lessonsId").getResultList();
		    List <CommentsDto> dtoList = new ArrayList<CommentsDto>();
		    for(Object[] obj:objList) {
		      int commentsId = Integer.parseInt(obj[0].toString());
		      
		      String name = (String)obj[1];
		      int lessonsId = Integer.parseInt(obj[2].toString());
		      String message = (String)obj[3];
		      Date date = (Date)(obj[4]);
		      Date modifiedDate = (Date)(obj[5]);

		      CommentsDto dto = new CommentsDto(commentsId,message,date,modifiedDate);
		      
		      dto.setUserAccountDto(new UserAccountDto(name));
		      
		      dto.setLessonsDto(new LessonsDto(lessonsId));
		      
		      dtoList.add(dto);
		    }
		    return dtoList;	
		  }

	@Override
	public void addComments(Comments comments) {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.getCurrentSession();
		    session.save(comments);    
		
	}
	 @Override
	  public void updateComments(Comments comments) {
	    // TODO Auto-generated method stub
	    Session session = sessionFactory.getCurrentSession();
	    session.update(comments);  

	 }
}
