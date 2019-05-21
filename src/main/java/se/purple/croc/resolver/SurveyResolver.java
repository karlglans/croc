package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import se.purple.croc.dto.FormDto;
import se.purple.croc.dto.ParticipantDto;
import se.purple.croc.dto.SurveyCountingSummaryDto;
import se.purple.croc.dto.SurveyDto;
import se.purple.croc.repository.SurveyRepository;
import se.purple.croc.service.FormService;
import se.purple.croc.service.SurveyService;

import java.util.List;


@Component
public class SurveyResolver implements GraphQLResolver<SurveyDto> {

	private SurveyService surveyService;

	private SurveyRepository surveyRepository;

	private FormService formService;

	public SurveyResolver(SurveyService surveyService, SurveyRepository surveyRepository, FormService formService) {
		this.surveyService = surveyService;
		this.surveyRepository = surveyRepository;
		this.formService = formService;
	}

	public List<ParticipantDto> getParticipants(SurveyDto survey) {
		return this.surveyService.getParticipants(survey);
	}

	public FormDto getForm(SurveyDto survey) {
		return this.formService.getFormDtoBySurveyId(survey.getId());
	}

	public SurveyCountingSummaryDto getSummary(SurveyDto survey) {
		return surveyService.getSummary(survey);
	}

}
