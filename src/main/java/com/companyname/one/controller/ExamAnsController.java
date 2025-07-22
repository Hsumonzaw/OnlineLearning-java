package com.companyname.one.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.one.dto.AnsDto;
import com.companyname.one.dto.CoursesDto;
import com.companyname.one.dto.ExamansDto;
import com.companyname.one.dto.QuizDto;
import com.companyname.one.service.ExamansService;
import com.companyname.one.service.UserAccountService;

@RestController
@RequestMapping("/api/v1/")
public class ExamAnsController {
	@Autowired
	ExamansService examService;

	@GetMapping("examans")
	public List<ExamansDto> getExamans() {
		try {
			return examService.getExamans();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("get,News Error!", e);
		}
	}

	@PostMapping("examans")
	public ExamansDto addExamans(@RequestBody ExamansDto dto) {
		try {
			return examService.addExamans(dto);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("create,News Error!", e);
		}
	}

	@PutMapping("examans/{examansId}")
	public ExamansDto updateExamans(@PathVariable("examansId") int examansId, @RequestBody ExamansDto dto) {
		try {

			dto.setExamId(examansId);
			return examService.updateExamans(dto);

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Update,Course Error!", e);
		}
	}

	@DeleteMapping("examans/{examansId}")
	public int deleteExamans(@PathVariable("examansId") int examansId) {
		try {
			return examService.deleteExamans(examansId);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
	
	@GetMapping("quiz")
	public List<QuizDto> getQuiz() {
		try {
			return examService.getQuiz();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("get,News Error!", e);
		}
	}

	@PostMapping("quiz")
	public int addQuiz(@RequestBody QuizDto dto) {
		try {
			return examService.addQuiz(dto);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("create,News Error!", e);
		}
	}

	@PutMapping("quiz/{quizId}")
	public int updateQuiz(@PathVariable("quizId") int quizId, @RequestBody QuizDto dto) {
		try {

			dto.setQuizId(quizId);
			return examService.updateQuiz(dto);

		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Update,Course Error!", e);
		}
	}

	@DeleteMapping("quiz/{quizId}")
	public int deleteQuiz(@PathVariable("quizId") int quizId) {
		try {
			return examService.deleteQuiz(quizId);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Delete Error!", e);
		}
	}
	@GetMapping("ans")
	public List<AnsDto> getAns() {
		try {
			return examService.getAns();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("get,News Error!", e);
		}
	}
}
