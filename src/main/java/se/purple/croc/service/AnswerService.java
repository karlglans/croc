package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.*;
import se.purple.croc.dto.AnswerDto;
import se.purple.croc.dto.AnswersSummary;
import se.purple.croc.dto.NumericAnswerDto;
import se.purple.croc.dto.SurveyDto;
import se.purple.croc.security.UserPrincipal;
import se.purple.croc.models.NestedHashMap;
import se.purple.croc.repository.AnswerRepository;
import se.purple.croc.service.exceptions.MissingData;

import java.util.*;
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

	@Autowired
	SurveyParticipantService surveyParticipantService;


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
		Users user = surveyService.getParticipant(survey, userId);
		FormQuestion formQuestion = formService.getFormQuestion(survey.getForm(), questionId);

		Answer answer = new Answer();
		answer.setValue(value);
		answer.setQuestion(formQuestion.getQuestion());
		answer.setResponder(user);
		answer.setSurvey(survey);
		survey.getAnswers().add(answer);
		return answer;
	}

	public AnswerDto updateAnswer(Integer surveyId, UserPrincipal authUser, Integer questionId, Integer value) {
		Answer answer = null;
		try {
			answer = getExistingAnswer(surveyId, authUser.getUserId(), questionId);
			answer.setValue(value);
			return makeAnswerDto(answer);
		} catch (MissingData ignored) {}
		answer = createAnswer(surveyId, authUser.getUserId(), questionId, value);
		surveyParticipantService.updateSurveyCompleteStatus(surveyId, authUser.getUserId());
		return makeAnswerDto(answer);
	}

	/**
	 *
	 * @param answers all answers given to the survey, that means multiple questions
	 * @param answersSumList outparameter, one item per question
	 * @return
	 */
	private void addNumericAnswers(final List<Answer> answers, List<AnswersSummary> answersSumList) {

		// ideer:
		// int occurrences = Collections.frequency(animals, "bat");
		// https://stackoverflow.com/questions/505928/how-to-count-the-number-of-occurrences-of-an-element-in-a-list

		NestedHashMap<Integer, Integer> countAnswers = new NestedHashMap<>();
		Set<Integer> questionIds = new HashSet<>();
		for (Answer answer : answers) {
			Integer questionId = answer.getQuestion().getId();
			questionIds.add(questionId);
			countAnswers.addCount(questionId, answer.getValue());
		}

		// will make an size-6-array like this:
		// int[] count = {0, 10, 2, 23, 14, 5 }; // this means for ex. 23 ppl answered: 4 on this question
		// the fields represent the number of answers corresponding to this position value

		for (Integer questionId : questionIds) {
			NumericAnswerDto answerDto = new NumericAnswerDto();
			answerDto.setQuestionId(questionId);

			int[] count = {1, 1, 1, 1, 1, 1 };
			count[0] = countAnswers.findCount(questionId, 1);
			count[1] = countAnswers.findCount(questionId, 2);
			count[2] = countAnswers.findCount(questionId, 3);
			count[3] = countAnswers.findCount(questionId, 4);
			count[4] = countAnswers.findCount(questionId, 5);
			count[5] = countAnswers.findCount(questionId, 6);

			answerDto.setCount(count);
			answerDto.makeContent();
			answersSumList.add(answerDto);
		}

		return;
	}

	// should add different types of answers to same list. The AnswersSummary.content may differ
	public List<AnswersSummary> getAnswersSummary(SurveyDto survey) {
		List<Answer> answers = answerRepo.getAnswersBySurveyId(survey.getId());
		List<AnswersSummary> answersSumList = new ArrayList<>();
		addNumericAnswers(answers, answersSumList);
		return answersSumList;
	}

}
