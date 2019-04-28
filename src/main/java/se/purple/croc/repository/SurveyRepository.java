package se.purple.croc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.Survey;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
	Survey findDistinctById(int id);

	@Query("Select a from Answer a JOIN FETCH a.survey s where s.id = 1")
	List<Answer> getAnswersBySurveyId(Integer surveyId);

}
