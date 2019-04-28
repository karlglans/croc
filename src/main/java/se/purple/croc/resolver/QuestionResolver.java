package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Question;
import se.purple.croc.repository.QuestionRepository;

@Component
public class QuestionResolver implements GraphQLResolver<Question> {
	private QuestionRepository questionRepo;

	public QuestionResolver(QuestionRepository questionRepo) {
		this.questionRepo = questionRepo;
	}

//	public Question getQuestion(int id) {
//		return this.questionRepo.getFirstById(id);
//	}

}