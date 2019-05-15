package se.purple.croc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.AnswerIdentity;
import se.purple.croc.domain.Survey;
import se.purple.croc.domain.Users;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, AnswerIdentity>, AnswerRepositoryExtension {
	List<Answer> getAnswerBySurveyIdAndResponderId(int survey, int user);
}
