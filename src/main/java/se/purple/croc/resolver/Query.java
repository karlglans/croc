package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.var;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.*;
import se.purple.croc.dto.*;
import se.purple.croc.repository.*;
import se.purple.croc.service.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
	private QuestionRepository questionRepo;
	private FormService formService;
	private UserGroupService userGroupService;
	private UserService userService;
	private SurveyService surveyService;
	private AnswerService answerService;

	public Query(QuestionRepository questionRepo, FormService formService,
				 UserGroupService userGroupService, UserService userService, SurveyService surveyService,
				 AnswerService answerService) {
		this.questionRepo = questionRepo;
		this.formService = formService;
		this.userGroupService = userGroupService;
		this.userService = userService;
		this.surveyService = surveyService;
		this.answerService = answerService;
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

	public QuestionDto getQuestion(int questionId) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.copy(questionRepo.findById(questionId).get());
		return questionDto;
	}
	public SurveyDto getSurvey(int surveyId) {
		return surveyService.getSurveyDtoById(surveyId);
	}
	public List<SurveyDto> getSurveys(SurveyStatus surveyStatus, Integer participantId) {
		return surveyService.getAllSurveyDtos(surveyStatus, participantId);
	}

	public Iterable<UserGroupDto> getUserGroups() {
		return userGroupService.getAllUserGroups();
	}
	public UserGroupDto getUserGroup(int id) {
		return userGroupService.getUserGroupDtoById(id);
	}

	public List<FormDto> getForms() {
		return formService.getAllFormDtos();
	}
	public FormDto getForm(int formId) {
		return formService.getFromDto(formId);
	}

	public long countQuestions() {
		return questionRepo.count();
	}

	public Iterable<UserDto> getUsers() {
		return userService.getAllUSers();
	}

	public Iterable<AnswerDto> getAnswers(Integer surveyId, Integer userId) {
		return answerService.getSurveyAnswersForParticipant(surveyId, userId);
	}
}
