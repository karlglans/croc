package se.purple.croc.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DebugInspect {
	@PersistenceContext
	EntityManager entityManager;

	void printTransactionInfo() {
		System.out.println("    -- QuestionService:getQuestionsByForm(): trans  " +
				TransactionSynchronizationManager.isSynchronizationActive()
				+ ", name:  " + TransactionSynchronizationManager.getCurrentTransactionName());
	}

	void printSession(String prefix) {
		Session session = entityManager.unwrap(Session.class);
		System.out.println(prefix + " Session " + session.getStatistics().toString());
	}
}
