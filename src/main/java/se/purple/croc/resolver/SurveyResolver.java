package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import se.purple.croc.dto.*;
import se.purple.croc.models.AuthenticatedUser;
import se.purple.croc.service.AuthService;
import se.purple.croc.service.FormService;
import se.purple.croc.service.SurveyParticipantService;
import se.purple.croc.service.SurveyService;

import java.util.List;


@Component
public class SurveyResolver implements GraphQLResolver<SurveyDto> {

	private SurveyService surveyService;
	private FormService formService;
	private SurveyParticipantService surveyParticipantService;
	private AuthService authService;

	public SurveyResolver(SurveyService surveyService, FormService formService,
						  SurveyParticipantService surveyParticipantService,
						  AuthService authService) {
		this.surveyService = surveyService;
		this.formService = formService;
		this.surveyParticipantService = surveyParticipantService;
		this.authService = authService;
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
}
