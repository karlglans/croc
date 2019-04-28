package se.purple.croc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.purple.croc.domain.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
	Question getFirstById(int Id);
}
