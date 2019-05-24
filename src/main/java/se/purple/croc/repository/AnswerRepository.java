package se.purple.croc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.AnswerIdentity;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, AnswerIdentity>, AnswerRepositoryExtension {
	List<Answer> getAnswerBySurveyIdAndResponderId(int survey, int user);

	// wip
	@Query("Select count(a) from Answer a JOIN a.responder r JOIN a.survey s " +
			"where r.id = :responderId AND s.id = :surveyId")
	int countSurveyAnswersForResponder(@Param("surveyId") int surveyId, @Param("responderId") int responderId);
}
