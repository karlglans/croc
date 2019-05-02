package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.var;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.*;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.dto.UserDto;
import se.purple.croc.dto.UserGroupDto;
import se.purple.croc.repository.*;
import se.purple.croc.service.FormService;
import se.purple.croc.service.UserGroupService;
import se.purple.croc.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
	private QuestionRepository questionRepo;
	private FromRepository formRepo;
	private SurveyRepository surveyRepo;
	private UserRepository userRepo;
	private FormService formService;
	private UserGroupService userGroupService;
	private UserService userService;

	public Query(QuestionRepository questionRepo, FromRepository formRepo, SurveyRepository surveyRepo,
				 UserRepository userRepository, FormService formService, UserGroupService userGroupService, UserService userService) {
		this.questionRepo = questionRepo;
		this.formRepo = formRepo;
		this.surveyRepo = surveyRepo;
		this.userRepo = userRepository;
		this.formService = formService;
		this.userGroupService = userGroupService;
		this.userService = userService;
	}

	public Iterable<QuestionDto> getQuestions() {
		var questions = questionRepo.findAll();
		List<QuestionDto> questionDtoList = new ArrayList<>();
		for(Question question : questions) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.copy(question);
			questionDtoList.add(questionDto);
		}
		return questionDtoList;
	}
	public Iterable<Form> getForms() {
		return formRepo.findAll();
	}

	public QuestionDto getQuestion(int questionId) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.copy(questionRepo.findById(questionId).get());
		return questionDto;
	}
	public Survey getSurvey(int surveyId) {
		return surveyRepo.findDistinctById(surveyId);
	}

	public Iterable<UserGroupDto> getUserGroups() {
		return userGroupService.getAllUserGroups();
	}
	public UserGroupDto getUserGroup(int id) {
		return userGroupService.getUserGroupDtoById(id);
	}

	public Form getForm(int formId) {
		return formService.getForm(formId);
	}

	public long countQuestions() {
		return questionRepo.count();
	}

	public Iterable<UserDto> getUsers() {
		return userService.getAllUSers();
	}
}
