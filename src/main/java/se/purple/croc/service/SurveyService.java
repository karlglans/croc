package se.purple.croc.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.purple.croc.domain.*;
import se.purple.croc.dto.ParticipantDto;
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

	@Transactional
	void addUserToSurvey(Integer userId, Integer SurveyId) throws ServiceException {
		Survey survey = surveyRepo.findDistinctById(SurveyId);
		if (survey == null) {
			throw new ServiceException("survey not found ");
		}

		Users user = userRepo.findDistinctById(userId);
		if (user == null) {
			throw new ServiceException("user not found ");
		}

		Form form = survey.getForm();

		List<FormQuestion> questionList = form.getFormQuestions();

		for(FormQuestion formQuestion: questionList) {
			Answer answer = new Answer();
			answer.setSurvey(survey);
			answer.setResponder(user);
			answer.setQuestion(formQuestion.getQuestion());
			answer.setValue(0);
			answerRepo.save(answer);
		}
	}

	@Transactional
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

	@Transactional
	public List<Answer> getAnswersBySurvey(Integer surveyId) {
		List<Answer> answers = surveyRepo.getAnswersBySurveyId(surveyId);
		return answers;
	}
}
