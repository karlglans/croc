package se.purple.croc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.UserGroup;
import se.purple.croc.dto.FormDto;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.repository.UserGroupRepository;
import se.purple.croc.service.FormService;
import se.purple.croc.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/form")
public class FormController {
	private FormService formService;

	private QuestionService questionService;

	private UserGroupRepository userGroupRepository;

	FormController(FormService formService, UserGroupRepository userGroupRepository, QuestionService questionService) {
		this.formService = formService;
		this.questionService = questionService;
		this.userGroupRepository = userGroupRepository;
	}

//	@GetMapping(value = "/{id}", produces = "application/json")
//	public FormDto getFormById(@PathVariable final int id) {
//		return formService.getFormById(id);
//	}

	// small test
//	@GetMapping(value = "/aa", produces = "application/json")
//	public List<QuestionDto> getFormQuestions() {
//		Form form = formService.getForm(1);
////		List<QuestionDto> questions = form.getFormQuestions();
//		return questionService.getQuestionsByForm(form);
//	}

//	@GetMapping(value = "/bb", produces = "application/json")
//	public List<UserGroup> getFormQuestions() {
//		List<UserGroup> list = userGroupRepository.findAll();
//		return list;
//	}
}
