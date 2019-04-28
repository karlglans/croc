package se.purple.croc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
@Sql(scripts = "/java/se/purple/croc/repository/data.sql")
public class FormRepoTests {

	@Autowired
	FromRepository fromRepository;

	@Autowired
	private TestEntityManager manager;

	@Test
	public void canAddForm() {
		Form form = new Form();
		form.setTitle("aaaa");
		fromRepository.save(form);

		Form from1 = manager.find(Form.class, form.getId());

		assertEquals(4, from1.getId());
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

//	@Test
//	public void canGetFormGroupByForm(){
//		Form form = fromRepository.getFirstById(1);
//		FormQuestionGroup formQuestionGroup = form.getQuestionGroups().get(0);
//
//		assertEquals(1, formQuestionGroup.getId());
//	}
}
