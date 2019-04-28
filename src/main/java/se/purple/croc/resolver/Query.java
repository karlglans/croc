package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.var;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.Question;
import se.purple.croc.domain.Survey;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.repository.FromRepository;
import se.purple.croc.repository.QuestionRepository;
import se.purple.croc.repository.SurveyRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
	private QuestionRepository questionRepo;
	private FromRepository formRepo;
	private SurveyRepository surveyRepo;

	public Query(QuestionRepository questionRepo, FromRepository formRepo, SurveyRepository surveyRepo) {
		this.questionRepo = questionRepo;
		this.formRepo = formRepo;
		this.surveyRepo = surveyRepo;
	}

	public Iterable<QuestionDto> getQuestions() {
		var questions = questionRepo.findAll();
		List<QuestionDto> questionDtoList = new ArrayList<>();
		for(Question question : questions) {
			QuestionDto questionDto = new QuestionDto();
			questionDto.copy(question);
			questionDtoList.add(questionDto);
		}

		return questionDtoList;
	}
	public Iterable<Form> getForms() {
		return formRepo.findAll();
	}

	public QuestionDto getQuestion(int questionId) {
		QuestionDto questionDto = new QuestionDto();
		questionDto.copy(questionRepo.findById(questionId).get());
		return questionDto;
	}
	public Survey getSurvey(int surveyId) {
		return surveyRepo.findDistinctById(surveyId);
	}

	public Form getForm(int formId) {
		return formRepo.findById(formId).get();
	}

	public long countQuestions() {
		return questionRepo.count();
	}
}
