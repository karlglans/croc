package se.purple.croc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.Answer;
import se.purple.croc.service.exceptions.ServiceException;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = "/java/se/purple/croc/service/data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = se.purple.croc.CrocApplication.class)
public class SurveyServiceTests {

	final Integer employee1Id = 3;
	final Integer survey1Id = 1;

	@Autowired
	SurveyService surveyService;

	@Test
	public void addUserToSurvey() {
		Exception ex = null;
		try {
			surveyService.addUserToSurvey(employee1Id, survey1Id);
		} catch (ServiceException e) {
			ex = e;
		}
		assertEquals(null, ex);
	}

	@Test
	public void getAnswerBySurvey() {
		List<Answer>  answers = surveyService.getAnswersBySurvey(1);
		assertEquals(1, answers.size());
	}
}
