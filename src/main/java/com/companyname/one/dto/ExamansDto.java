package com.companyname.one.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.companyname.one.domain.Courses;
import com.companyname.one.domain.Examans;
import com.companyname.one.util.DateFormatDeserializer;
import com.companyname.one.util.DateTimeFormatDeserializer;
import com.companyname.one.util.DateTimeFormatSerializer;
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
public class ExamansDto implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int examId;

	private UserAccountDto userAccountDto;
	private CoursesDto coursesDto;
	private LanguagesDto languagesDto;

	private int examMark;
	@JsonSerialize(using = DateTimeFormatSerializer.class)
	@JsonDeserialize(using = DateTimeFormatDeserializer.class)
	private Date date;
	private String status;
	
//	public ExamansDto(Examans exam) {
//		this.examId = exam.getExamId();
//		this.pdf = exam.getPdf();
//		this.exammark = exam.getExamId();
//		this.date = exam.getDate();
//		this.status = exam.getStatus();
//		}
	public ExamansDto(int examId,int examMark, Date date,String status) {
		this.examId = examId;
		this.examMark =examMark;
		this.date =date;
		this.status = status;
	}

	public ExamansDto(int examId, String name, int languagesId, String languagesName, int examMark, Date date,
			String status) {
		// TODO Auto-generated constructor stub
		this.examId = examId;
		this.userAccountDto = new UserAccountDto(name);
		this.languagesDto= new LanguagesDto(languagesId,languagesName);
		this.examMark =examMark;
		this.date =date;
		this.status = status;
		
	
	}

	
	
//	public void setUserAccountDto(UserAccountDto userAccountDto) {
//		
//		
//	}
//	
//	public int getExamId() {
//		return examId;
//	}
//	public void setExamId(int examId) {
//		this.examId = examId;
//	}
////	public Integer getUserAccountId() {
////		return userAccountId;
////	}
////	public void setUserAccountId(Integer userAccountId) {
////		this.userAccountId = userAccountId;
////	}
////	public Integer getCoursesId() {
////		return coursesId;
////	}
////	public void setCoursesId(Integer coursesId) {
////		this.coursesId = coursesId;
////	}
//	public String getPdf() {
//		return pdf;
//	}
//	public void setPdf(String pdf) {
//		this.pdf = pdf;
//	}
//	public int getExammark() {
//		return exammark;
//	}
//	public void setExammark(int exammark) {
//		this.exammark = exammark;
//	}
//	public Date getDate() {
//		return date;
//	}
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}






	
	
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((examid == null) ? 0 : examid.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		Examans other = (Examans) obj;
//		if (examid == null) {
//			if (other.examid != null) {
//				return false;
//			}
//		} else if (!examid.equals(other.examid)) {
//			return false;
//		}
//		return true;
//	}

}
