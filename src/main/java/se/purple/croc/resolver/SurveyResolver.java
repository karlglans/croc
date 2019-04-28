package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.Survey;
import se.purple.croc.dto.ParticipantDto;
import se.purple.croc.repository.SurveyRepository;
import se.purple.croc.service.SurveyService;

import java.util.List;


@Component
public class SurveyResolver implements GraphQLResolver<Survey> {

	private SurveyService surveyService;

	private SurveyRepository surveyRepository;

	public SurveyResolver(SurveyService surveyService, SurveyRepository surveyRepository) {
		this.surveyService = surveyService;
		this.surveyRepository = surveyRepository;
	}

	public List<ParticipantDto> getParticipants(Survey survey) {
		return surveyService.getParticipants(survey);
	}

	public Form getForm(Survey survey) {
		return survey.getForm();
	}

}
