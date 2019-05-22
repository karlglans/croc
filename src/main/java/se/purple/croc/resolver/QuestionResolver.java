package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Question;
import se.purple.croc.dto.QuestionDto;
import se.purple.croc.dto.QuestionUserPrivileges;
import se.purple.croc.repository.FormQuestionRepository;
import se.purple.croc.repository.QuestionRepository;

@Component
public class QuestionResolver implements GraphQLResolver<QuestionDto> { // Question
	private QuestionRepository questionRepo;

	private FormQuestionRepository formQuestionRepo;

	public QuestionResolver(QuestionRepository questionRepo, FormQuestionRepository formQuestionRepo) {
		this.questionRepo = questionRepo;
		this.formQuestionRepo = formQuestionRepo;
	}

	Question getQuestion(int questionId) {
		return questionRepo.getFirstById(questionId);
	}

	// getQuestionUserPrivileges
	public QuestionUserPrivileges getPrivileges(QuestionDto questionDto) {
		boolean isQuestionEditable = formQuestionRepo.getFormQuestionsByQuestionId(questionDto.getId()).size() == 1;

		QuestionUserPrivileges questionUserPrivileges = new QuestionUserPrivileges();
		questionUserPrivileges.setEditable(isQuestionEditable);
		return questionUserPrivileges;
	}

}