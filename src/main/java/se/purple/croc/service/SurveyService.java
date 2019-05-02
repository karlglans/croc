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

	@PersistenceContext
	private EntityManager manager;

	void addAnserToSurvey(Integer userId, Integer SurveyId, Integer questionId) {

	}

	public List<SurveyDto> getAllServeyDtos() {
		var surveys = surveyRepo.findAll();
		List<SurveyDto> surveyDtos = new ArrayList<>();
		for (Survey survey: surveys) {
			surveyDtos.add(makeSurveyDto(survey));
		}
		return surveyDtos;
	}

	SurveyDto makeSurveyDto(Survey survey) {
		SurveyDto surveyDto = new SurveyDto();
		surveyDto.setId(survey.getId());
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

	public List<ParticipantDto> getParticipants(Survey survey){
		survey = surveyRepo.findDistinctById(survey.getId());
		List<ParticipantDto> participantDtos = new ArrayList<>();
		for(Users user : survey.getParticipants()) {
			ParticipantDto participant = new ParticipantDto();
			participant.populate(user, survey);
			participantDtos.add(participant);
		}
		return participantDtos;
	}

	public List<Answer> getAnswersBySurvey(Integer surveyId) {
		List<Answer> answers = surveyRepo.getAnswersBySurveyId(surveyId);
		return answers;
	}
}
