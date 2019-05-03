package se.purple.croc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;

import java.util.List;


@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
	Question getFirstById(int Id);

	// TODO check if this can be more optimized
	@Query("Select fq from Form f JOIN f.formQuestions fq where fq.form.id = :formId")
	List<FormQuestion> getQuestionsByFormId(@Param("formId") Integer formId);
}
