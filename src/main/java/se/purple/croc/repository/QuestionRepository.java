package se.purple.croc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import se.purple.croc.domain.Question;
import se.purple.croc.domain.Users;

import java.util.List;

//@Transactional
@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer> {
	Question getFirstById(int Id);


//	@Query("Select u from Question q JOIN FETCH q. g where g.id = :userId")
//	List<Question> getQuestionsByFromId(@Param("userId") Integer groupId);
}
