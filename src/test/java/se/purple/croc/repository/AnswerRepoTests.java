package se.purple.croc.repository;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class AnswerRepoTests {

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	private TestEntityManager manager;


	/**
	 * Trying to get (survey_id, responder_id, question_id, value) VALUES (1, 3, 1, 2)
	 * from test set
	 */
	@Test
	public void canFindAnswer() {
		AnswerIdentity answerIdentity = new AnswerIdentity();
		answerIdentity.setSurvey(1);
		answerIdentity.setResponder(4);
		answerIdentity.setQuestion(1);

		Optional<Answer> answerFromTestSet = answerRepository.findById(answerIdentity);
		assertTrue(answerFromTestSet.isPresent());
		assertEquals(2, answerFromTestSet.get().getValue().intValue());
	}

	@Test
	public void getAnswerBySurveyAndResponder() {
		List<Answer> answers = answerRepository.getAnswerBySurveyIdAndResponderId(1, 4);
		assertEquals(2, answers.size());
	}


	@Test
	public void countSurveyAnswersForResponder() {
		int nbAnswers = answerRepository.countSurveyAnswersForResponder(1, 4);
		assertEquals(2, nbAnswers);
	}

	@Test
	public void getAnswersBySurveyId() {
		var answersSurvey3 = answerRepository.getAnswersBySurveyId(3);
		assertEquals(6, answersSurvey3.size()); // 3 participants, 3 answers per participant
	}

}
