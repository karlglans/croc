package se.purple.croc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;

import java.util.List;

import static org.junit.Assert.assertEquals;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class FormRepoTests {

	@Autowired
	FromRepository fromRepository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	private TestEntityManager manager;

	@Test
	public void canAddForm() {
		Form form = new Form();
		form.setTitle("aaaa");
		fromRepository.save(form);

		Form from1 = manager.find(Form.class, form.getId());

		assertEquals(5, from1.getId());
	}

	@Test
	public void canFindFromForSurvey() {
		Form form = fromRepository.getFromBySurveyId(3);
		assertEquals(2, form.getId());
	}

	@Test
	public void canGetQuestionsFromForm() {
		List<FormQuestion> questions = questionRepository.getQuestionsByFormId(1);
		assertEquals(2, questions.size());
	}

	@Test
	public void canAddAnotherQuestionToForm() {
		Question question3 = manager.find(Question.class, 3);
		Form form1 = manager.find(Form.class, 1);

		FormQuestion formQuestion = new FormQuestion();
		formQuestion.setForm(form1);
		formQuestion.setQuestion(question3);
		formQuestion.setNumber(3);

		int preNumbQuestions = form1.getFormQuestions().size();
		form1.getFormQuestions().add(formQuestion);

		manager.persistAndFlush(form1);

		form1 = manager.find(Form.class, 1);
		assertEquals(preNumbQuestions + 1, form1.getFormQuestions().size());
	}

}
