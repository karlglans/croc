package se.purple.croc.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.Form;
import se.purple.croc.dto.ParticipantDto;

import java.util.ArrayList;
import java.util.List;


@Component
public class AnswerResolver implements GraphQLResolver<Answer> {

//	public List<Answer> getAnswers(ParticipantDto participantDto) {
//		List<Answer> answers = new ArrayList<>();
//		return answers;
//	}

}
