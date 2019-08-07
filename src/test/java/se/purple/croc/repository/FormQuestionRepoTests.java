package se.purple.croc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;


import static org.junit.Assert.assertEquals;

import java.util.List;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class FormQuestionRepoTests {

	@Autowired
	FormQuestionRepository formQuestionRepo;

	@Test
	public void canGetFormsByQuestion() {

		List<FormQuestion> formQuestions = formQuestionRepo.getFormQuestionsByQuestionId(2);
		assertEquals(2, formQuestions.size());
	}
}
