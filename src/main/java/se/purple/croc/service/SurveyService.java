package se.purple.croc.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.*;
import se.purple.croc.dto.OwnSurveyStatusDto;
import se.purple.croc.dto.SurveyCountingSummaryDto;
import se.purple.croc.dto.SurveyDto;
import se.purple.croc.security.AuthHelper;
import se.purple.croc.security.UserPrincipal;
import se.purple.croc.repository.AnswerRepository;
import se.purple.croc.repository.SurveyParticipantRepository;
import se.purple.croc.repository.SurveyRepository;
import se.purple.croc.repository.UserRepository;
import se.purple.croc.service.exceptions.MissingData;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Autowired
	AuthService authService;

	@Autowired
	SurveyParticipantRepository surveyParticipantRepo;

	@Autowired
	SurveyParticipantService surveyParticipantService;

	@Autowired
	AuthHelper authHelper;


	Users getParticipant(Survey survey, int userId)  {
		Users participant = surveyRepo.getUserInSurvey(userId, survey.getId());
		if (participant == null) {
			throw new ServiceException("user is not participating in survey");
		}
		return participant;
	}

	public boolean startSurvey(Integer surveyId) {
		var survey = this.getSurveyById(surveyId);
		survey.setStatus(SurveyStatus.ONGOING);
		return true;
	}

	// will run some expensive counting queries, think of another way to store this data
	private void addCountingInfoToSurvey(SurveyDto survey) {
		// 1 count participants
		// 2 count answers
		// 3 count questions
		// 4 number of answering participants
	}

	public List<SurveyDto> getAllSurveyDtos(SurveyStatus surveyStatus, Integer participantId,
											Boolean isParticipating, UserPrincipal authenticatedUser) {
		// TODO: make different kind of selections based on existence of params
		List<Survey> surveys;

		if (authHelper.checkRole(authenticatedUser, Role.supervisor)) {
			surveys = surveyRepo.findSurveyByStatusEquals(surveyStatus);
		}
		else if (authHelper.checkRole(authenticatedUser, Role.user)){
			surveys = surveyRepo.findSurveyByParticipantsEquals(authenticatedUser.getUserId());
		} else {
			surveys = surveyRepo.findSurveyByStatusEquals(surveyStatus);
		}

		return surveys.stream()
				.map(survey -> makeSurveyDto(survey))
				.collect(Collectors.toList());
	}

	SurveyDto makeSurveyDto(Survey survey) {
		SurveyDto surveyDto = new SurveyDto();
		surveyDto.setId(survey.getId());
		surveyDto.setName(survey.getName());
		return surveyDto;
	}

	Survey getSurveyById(int id) {
		Optional<Survey> survey = surveyRepo.findById(id);
		if (!survey.isPresent()) {
			throw new MissingData(String.format("survey %d not found", id));
		}
		return survey.get();
	}

	public SurveyDto getSurveyDtoById(int id) {
		return makeSurveyDto(getSurveyById(id));
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

	public List<Answer> getAnswersBySurvey(Integer surveyId) {
		return surveyRepo.getAnswersBySurveyId(surveyId);
	}

	public boolean addUsersToSurvey(int userGroupId, int surveyId) {
		Survey survey = getSurveyById(surveyId);
		var users =  userRepo.findUsersByGroupId(userGroupId);
		var usersInSurvey = surveyRepo.findSurveyParticipantsUsersBySurveyId(surveyId);
		// will not add user again if already there.
		users.removeAll(usersInSurvey);
		// Maybe make special query for this task instead of loop
		for (Users user : users) {
			SurveyParticipant sp = new SurveyParticipant();
			sp.setSurvey(survey);
			sp.setParticipant(user);
			sp.setComplete(false);
			surveyParticipantRepo.save(sp);
		}
		return true; // TODO find better
	}

	public SurveyDto createSurvey(int formId, String name) {
		Form form = formService.getForm(formId);
		Survey survey = new Survey();
		survey.setForm(form);
		survey.setName(name);
		surveyRepo.save(survey);
		return makeSurveyDto(survey);
	}

	public SurveyCountingSummaryDto getSummary(SurveyDto survey) {
		SurveyCountingSummaryDto summary = new SurveyCountingSummaryDto();
		// TODO make one query
		summary.setNbAnsweringParticipants(surveyRepo.countAnsweringParticipantsInSurvey(survey.getId()));
		summary.setNbParticipants(surveyRepo.countParticipantSurvey(survey.getId()));
		return summary;
	}

	public OwnSurveyStatusDto getOwnStatus(int userId, int surveyId) {
		OwnSurveyStatusDto ownSurveyStatus = new OwnSurveyStatusDto();
		int nAnswers = answerRepo.countSurveyAnswersForResponder(surveyId, userId);
		int nQuestions = surveyRepo.getNumberOfQuestionsInSurvey(surveyId);
		ownSurveyStatus.setCompletedAnswers(nAnswers == nQuestions);
		return ownSurveyStatus;
	}
}
