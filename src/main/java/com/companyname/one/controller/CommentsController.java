package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.one.dto.CommentsDto;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.RatingsDto;
import com.companyname.one.service.CommentsService;
import com.companyname.one.service.CoursesService;

@RestController
@RequestMapping("/api/v1/")
public class CommentsController {
	@Autowired
	CommentsService comService;
	
	@GetMapping("comments")
	public List<CommentsDto> getComments(){
		return comService.getComments();
	}
	@PostMapping("comments")
	  public CommentsDto addComments(@RequestBody CommentsDto dto) {
	    try 
	    {
	      return comService.addComments(dto);
	    }catch (Exception e) {
	      // TODO: handle exception
	      throw new RuntimeException("Add,Rating is Error!", e);
	    }
	  }
	@PutMapping("comments/{commentsId}")
	  public CommentsDto updateComments(@PathVariable("commentsId")int commentsId,
	      @RequestBody CommentsDto dto) {
	    try {
	            dto.setCommentsId(commentsId);
	      return comService.updateComments(dto);  
	      
	    }catch (Exception e) {
	      // TODO: handle exception
	      throw new RuntimeException("Update,Course Error!", e);
	    }
	  }

}
