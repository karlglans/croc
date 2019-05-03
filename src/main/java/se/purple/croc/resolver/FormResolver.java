package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import se.purple.croc.dto.FormDto;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.service.FormService;
import se.purple.croc.service.QuestionService;

import java.util.List;

@Component
public class FormResolver implements GraphQLResolver<FormDto> {
	private FormService formService;
	private QuestionService questionService;

	public FormResolver(FormService formService, QuestionService questionService ) {
		this.formService = formService;
		this.questionService = questionService;
	}

	public List<QuestionDto> getQuestions(FormDto form) {
		return questionService.getQuestionsByForm(form.getId());
	}
}
