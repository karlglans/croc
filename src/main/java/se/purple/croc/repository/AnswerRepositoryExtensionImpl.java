package se.purple.croc.repository;

import org.springframework.stereotype.Service;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.AnswerIdentity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AnswerRepositoryExtensionImpl implements AnswerRepositoryExtension {
	@PersistenceContext
	private EntityManager manager;

	// not in use
	public Answer findAnswerByIdentity(AnswerIdentity answerIdentity) {
		return manager.find(Answer.class, answerIdentity);
	}

	public void saveAnswer(Answer answer) {
		manager.persist(answer);
	}
}
