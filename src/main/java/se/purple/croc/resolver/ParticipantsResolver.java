package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Answer;
import se.purple.croc.dto.AnswerDto;
import se.purple.croc.dto.ParticipantAnswerDto;
import se.purple.croc.dto.ParticipantDto;
import se.purple.croc.service.SurveyService;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParticipantsResolver implements GraphQLResolver<ParticipantDto> {

	@Autowired
	SurveyService surveyService;

	public List<AnswerDto> getAnswers(ParticipantDto participantDto) {

		List<Answer> answers = surveyService.getAnswersBySurvey(participantDto.getSurveyId());
		List<AnswerDto> participantAnswerDtos = new ArrayList<>();

		for(Answer answer : answers) {
			AnswerDto participantAnswer = new AnswerDto();
			participantAnswer.setValue(answer.getValue());
			participantAnswer.setParticipantId(answer.getResponder().getId());
			participantAnswer.setQuestionId(answer.getQuestion().getId());
			participantAnswerDtos.add(participantAnswer);
		}

		return participantAnswerDtos;
	}

}
