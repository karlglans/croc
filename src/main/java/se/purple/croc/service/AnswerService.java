package se.purple.croc.service;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.*;
import se.purple.croc.dto.AnswerDto;
import se.purple.croc.models.AuthenticatedUser;
import se.purple.croc.repository.AnswerRepository;
import se.purple.croc.service.exceptions.MissingData;

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

	@Autowired
	AuthService authService;


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

	private Answer getExistingAnswer(Integer surveyId, Integer userId, Integer questionId) {
		AnswerIdentity answerIdentity = new AnswerIdentity();
		answerIdentity.setSurvey(surveyId);
		answerIdentity.setResponder(userId);
		answerIdentity.setQuestion(questionId);
		Optional<Answer> foundAnswer = answerRepo.findById(answerIdentity);
		if (!foundAnswer.isPresent()){
			throw new MissingData("Missing answer");
		}
		return foundAnswer.get();
	}

	private Answer createAnswer(Integer surveyId, Integer userId, Integer questionId, Integer value) {
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

	public AnswerDto updateAnswer(Integer surveyId, AuthenticatedUser authUser, Integer questionId, Integer value) {
		try {
			Answer existingAnswer = getExistingAnswer(surveyId, authUser.getUserId(), questionId);
			existingAnswer.setValue(value);
			return makeAnswerDto(existingAnswer);
		} catch (MissingData ignored) {}
		return makeAnswerDto(createAnswer(surveyId, authUser.getUserId(), questionId, value) );
	}

}
