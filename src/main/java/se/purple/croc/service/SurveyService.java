package se.purple.croc.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.*;
import se.purple.croc.dto.ParticipantDto;
import se.purple.croc.dto.SurveyDto;
import se.purple.croc.repository.AnswerRepository;
import se.purple.croc.repository.SurveyRepository;
import se.purple.croc.repository.UserRepository;
import se.purple.croc.service.exceptions.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

	@Autowired
	SurveyRepository surveyRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	AnswerRepository answerRepo;

	@Autowired
	FormService formService;

	@PersistenceContext
	private EntityManager manager;

	void addAnserToSurvey(Integer userId, Integer SurveyId, Integer questionId) {

	}

	public List<SurveyDto> getAllSurveyDtos(SurveyStatus surveyStatus) {
		var surveys = surveyRepo.findSurveyByStatusEquals(surveyStatus);
		List<SurveyDto> surveyDtos = new ArrayList<>();
		for (Survey survey: surveys) {
			surveyDtos.add(makeSurveyDto(survey));
		}
		return surveyDtos;
	}

	SurveyDto makeSurveyDto(Survey survey) {
		SurveyDto surveyDto = new SurveyDto();
		surveyDto.setId(survey.getId());
		surveyDto.setName(survey.getName());
		return surveyDto;
	}

	public Survey getSurveyById(int id) {
		return surveyRepo.getOne(id);
	}

	public SurveyDto getSurveyDtoById(int id) {
		SurveyDto surveyDto = makeSurveyDto(getSurveyById(id));
		return surveyDto;
	}

	void addUserToSurvey(Integer userId, Integer SurveyId) throws ServiceException {
		Survey survey = surveyRepo.findDistinctById(SurveyId);
		if (survey == null) {
			throw new ServiceException("survey not found ");
		}

		Optional<Users> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			throw new ServiceException("user not found ");
		}

		Form form = survey.getForm();

		List<FormQuestion> questionList = form.getFormQuestions();

		for(FormQuestion formQuestion: questionList) {
			Answer answer = new Answer();
			answer.setSurvey(survey);
			answer.setResponder(user.get());
			answer.setQuestion(formQuestion.getQuestion());
			answer.setValue(0);
			answerRepo.save(answer);
		}
	}

	public List<ParticipantDto> getParticipants(SurveyDto survey){
		final int surveyId = survey.getId();
		List<ParticipantDto> participantDtos = new ArrayList<>();
		var users = surveyRepo.findParticipantsBySurveyId(survey.getId());
		for(Users user : users) {
			ParticipantDto participant = new ParticipantDto();
			participant.setSurveyId(surveyId);
			participant.setId(user.getId());
			participant.setEmail(user.getEmail());
			participantDtos.add(participant);
		}
		return participantDtos;
	}

	public List<Answer> getAnswersBySurvey(Integer surveyId) {
		List<Answer> answers = surveyRepo.getAnswersBySurveyId(surveyId);
		return answers;
	}

	public boolean addUsersToSurvey(int userGroupId, int surveyId) {
		Survey survey = getSurveyById(surveyId);
		var users =  userRepo.findUsersByGroupId(userGroupId);
		return survey.getParticipants().addAll(users);
	}

	public SurveyDto createSurvey(int formId, String name) {
		Form form = formService.getForm(formId);
		Survey survey = new Survey();
		survey.setForm(form);
		survey.setName(name);
		surveyRepo.save(survey);
		return makeSurveyDto(survey);
	}
}
