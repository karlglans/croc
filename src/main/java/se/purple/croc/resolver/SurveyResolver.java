package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import se.purple.croc.dto.*;
import se.purple.croc.models.AuthenticatedUser;
import se.purple.croc.service.*;

import java.util.ArrayList;
import java.util.List;


@Component
public class SurveyResolver implements GraphQLResolver<SurveyDto> {

	private SurveyService surveyService;
	private FormService formService;
	private SurveyParticipantService surveyParticipantService;
	private AnswerService answerService;
	private AuthService authService;

	public SurveyResolver(SurveyService surveyService, FormService formService,
						  SurveyParticipantService surveyParticipantService,
						  AuthService authService, AnswerService answerService) {
		this.surveyService = surveyService;
		this.formService = formService;
		this.surveyParticipantService = surveyParticipantService;
		this.authService = authService;
		this.answerService = answerService;
	}

	public List<ParticipantDto> getParticipants(SurveyDto survey) {
		return surveyParticipantService.getParticipants(survey);
	}

	public FormDto getForm(SurveyDto survey) {
		return this.formService.getFormDtoBySurveyId(survey.getId());
	}

	public SurveyCountingSummaryDto getSummary(SurveyDto survey) {
		return surveyService.getSummary(survey);
	}

	public OwnSurveyStatusDto getOwnStatus(SurveyDto survey) {
		AuthenticatedUser authUser = authService.getPrincipal();
		return surveyService.getOwnStatus(authUser.getUserId(), survey.getId());
	}

	public List<AnswersSummary> getAnswersSum(SurveyDto survey) {
//		List<AnswersSummary> numericAnswersList = new ArrayList<>();
//		NumericAnswerDto answerDto = new NumericAnswerDto();
////		int[] count = {0, 1, 2, 3, 4, 5 };
////		answerDto.setCount(count);
//		answerDto.setQuestionId(1);
//		answerDto.makeContent();
//		numericAnswersList.add(answerDto);
		return answerService.getAnswersSummary(survey);
	}
}
