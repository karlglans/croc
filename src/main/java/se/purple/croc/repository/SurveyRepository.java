package se.purple.croc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.Survey;
import se.purple.croc.domain.SurveyStatus;

import java.util.List;
import java.util.Set;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {
	Survey findDistinctById(int id);

	@Query("Select a from Answer a JOIN FETCH a.survey s where s.id = 1")
	List<Answer> getAnswersBySurveyId(Integer surveyId);


	@Query("Select s from Survey s where s.status = :surveyStatus")
	List<Survey> findSurveyByStatusEquals(@Param("surveyStatus") SurveyStatus surveyStatus);

//	Set<Survey> findSurveyByStatusEquals(SurveyStatus surveyStatus);

}
