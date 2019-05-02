package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.purple.croc.domain.Form;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.service.FormService;
import se.purple.croc.service.QuestionService;

import java.util.List;

//@Transactional
@Component
public class FormResolver implements GraphQLResolver<Form> {
	private FormService formService;
	private QuestionService questionService;

	public FormResolver(FormService formService, QuestionService questionService ) {
		this.formService = formService;
		this.questionService = questionService;
	}

//	@Transactional
	public List<QuestionDto> getQuestions(Form form) {
		return questionService.getQuestionsByForm(form);
	}
}
