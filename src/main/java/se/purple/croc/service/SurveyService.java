package se.purple.croc.service;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.*;
import se.purple.croc.dto.ParticipantDto;
import se.purple.croc.dto.SurveyDto;
import se.purple.croc.models.AuthenticatedUser;
import se.purple.croc.repository.AnswerRepository;
import se.purple.croc.repository.SurveyRepository;
import se.purple.croc.repository.UserRepository;
import se.purple.croc.service.exceptions.MissingData;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.ArrayList;
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


	Users getResponder(Survey survey, int userId)  {
		// TODO make query
		Optional<Users> user = survey.getParticipants().stream().filter(u -> u.getId() == userId).findFirst();
		if (!user.isPresent()) {
			throw new ServiceException("user is not participating in survey");
		}
		return user.get();
	}

	public boolean startSurvey(Integer surveyId) {
		var survey = this.getSurveyById(surveyId);
		survey.setStatus(SurveyStatus.ONGOING);
		return true;
	}

	public List<SurveyDto> getAllSurveyDtos(SurveyStatus surveyStatus, Integer participantId,
											Boolean isParticipating, AuthenticatedUser authenticatedUser) {
		// TODO: make different kind of selections based on existence of params
		List<Survey> surveys;

		// boolean isSelectingSurveysWhereClientIsParticipating = isParticipating != null && isParticipating;ö

		if (authenticatedUser.hasRole(Role.supervisor)) {
			surveys = surveyRepo.findSurveyByStatusEquals(surveyStatus);
		}
		else if (authenticatedUser.hasRole(Role.user)){
			Users users = new Users();
			users.setId((int)authenticatedUser.getUserId());
			surveys = surveyRepo.findSurveyByParticipantsEquals(users);
		} else {
			surveys = surveyRepo.findSurveyByStatusEquals(surveyStatus);
		}

		return surveys.stream()
//				.filter(survey -> participantId == null ? true : participantId.intValue() == survey.getId())
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
