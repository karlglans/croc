package se.purple.croc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Form;

@Repository
public interface FromRepository extends JpaRepository<Form, Integer> {
	Form getFirstById(int Id);

	@Query("Select s.form from Survey s JOIN s.form f where s.id = :surveyId")
	Form getFromBySurveyId(@Param("surveyId") Integer surveyId);
}
