package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.AnswerIdentity;
import se.purple.croc.dto.AnswerDto;
import se.purple.croc.repository.AnswerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

	@Autowired
	AnswerRepository answerRepo;

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

}
