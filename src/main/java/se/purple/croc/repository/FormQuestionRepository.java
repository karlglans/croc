package se.purple.croc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.FormQuestion;

import java.util.List;

@Repository
public interface FormQuestionRepository extends JpaRepository<FormQuestion, Integer> {
	List<FormQuestion> getFormQuestionsByQuestionId(int questionId);
}
