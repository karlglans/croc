package se.purple.croc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = se.purple.croc.CrocApplication.class)
public class FormServiceTests {

	@Autowired
	FormService formService;

	@Test
	public void addQuestionToForm() {
		int questionPositionNumber = formService.addQuestionToForm(3, 1);
		assertEquals(3, questionPositionNumber);
	}

	@Test
	public void addQuestionToForm_whenAlreadyThere() {
		int questionPositionNumber = formService.addQuestionToForm(2, 1);
		assertEquals(0, questionPositionNumber);
	}
}
