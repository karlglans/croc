package se.purple.croc.repository;

import se.purple.croc.domain.Answer;
import se.purple.croc.domain.AnswerIdentity;

public interface AnswerRepositoryExtension {
	Answer findAnswerByIdentity(AnswerIdentity answerIdentity);
	void saveAnswer(Answer answer);
}
