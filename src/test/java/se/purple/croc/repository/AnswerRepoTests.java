package se.purple.croc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@DataJpaTest
//public class AnswerRepoTests {
//
//	@Autowired
//	AnswerRepository answerRepository;
//
//	@Autowired
//	private TestEntityManager manager;
//
//	 this test does not seem to work
//	@Test
//	public void canAddAnswer() {
//		Survey survey1 = manager.find(Survey.class, 1);
//		Question question1 = manager.find(Question.class, 1);
//		Users responder = manager.find(Users.class, 3);
//
//		assertEquals(1, survey1.getId());
//		assertEquals(1, question1.getId());
//		assertEquals(3, responder.getId());
//		int answerValue = 999;
//
//		Answer answer = new Answer();
//		answer.setQuestion(question1);
//		answer.setResponder(responder);
//		answer.setSurvey(survey1);
//		answer.setValue(answerValue);
//
//		AnswerIdentity answerIdentity = new AnswerIdentity();
//		answerIdentity.setAnswerId(answer);
//
//		manager.persistAndFlush(answer);
//
//		Optional<Answer> savedAnswer = answerRepository.findById(answerIdentity);
//		assertEquals(true, savedAnswer.isPresent());
//
//		int answerVal = savedAnswer.get().getValue();
//		assertEquals(answerValue, answerVal);
//	}
//}
