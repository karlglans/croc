package se.purple.croc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.AnswerIdentity;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, AnswerIdentity>, AnswerRepositoryExtension {
}
