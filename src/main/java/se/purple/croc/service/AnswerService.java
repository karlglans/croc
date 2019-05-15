package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.*;
import se.purple.croc.dto.AnswerDto;
import se.purple.croc.repository.AnswerRepository;
import se.purple.croc.repository.SurveyRepository;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService {

	@Autowired
	AnswerRepository answerRepo;

	@Autowired
	SurveyService surveyService;

	@Autowired
	FormService formService;


	AnswerDto makeAnswerDto(Answer answer) {
		AnswerDto answerDto = new AnswerDto();
		answerDto.setValue(answer.getValue());
		answerDto.setParticipantId(answer.getResponder().getId());
		answerDto.setQuestionId(answer.getQuestion().getId());
		return answerDto;
	}

	public List<AnswerDto> getSurveyAnswersForParticipant(Integer surveyId, Integer userId) {
		List<Answer> answer = answerRepo.getAnswerBySurveyIdAndResponderId(surveyId, userId);
		return answer.stream().map(this::makeAnswerDto)
				.collect(Collectors.toList());
	}

	private Optional<Answer> getExistingAnswer(Integer surveyId, Integer userId, Integer questionId) {
		AnswerIdentity answerIdentity = new AnswerIdentity();
		answerIdentity.setSurvey(surveyId);
		answerIdentity.setResponder(userId);
		answerIdentity.setQuestion(questionId);
		return answerRepo.findById(answerIdentity);
	}

	private Answer createAnswer(Integer surveyId, Integer userId, Integer questionId, Integer value) throws ServiceException {
		Survey survey = surveyService.getSurveyById(surveyId);
		Users user = surveyService.getResponder(survey, userId);
		FormQuestion formQuestion = formService.getFormQuestion(survey.getForm(), questionId);

		Answer answer = new Answer();
		answer.setValue(value);
		answer.setQuestion(formQuestion.getQuestion());
		answer.setResponder(user);
		answer.setSurvey(survey);
		survey.getAnswers().add(answer);
		return answer;
	}

	public AnswerDto updateAnswer(Integer surveyId, Integer userId, Integer questionId, Integer value) throws ServiceException {
		Optional<Answer> foundAnswer = getExistingAnswer(surveyId, userId, questionId);
		if (foundAnswer.isPresent()) {
			foundAnswer.get().setValue(value);
			return makeAnswerDto(foundAnswer.get());
		}
		return makeAnswerDto(createAnswer(surveyId, userId, questionId, value) );
	}

}
