package se.purple.croc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.Form;
import se.purple.croc.domain.Survey;
import se.purple.croc.domain.SurveyStatus;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
@Sql(scripts = "/testdata/data.sql")
public class SurveyRepoTests {

	@Autowired
	private SurveyRepository surveyRepository;

	@Autowired
	private TestEntityManager manager;

	@Test
	public void canAddSurvey() {
		Survey survey = new Survey();
		surveyRepository.save(survey);
		Survey survey1 = manager.find(Survey.class, survey.getId());
		assertEquals(5, survey1.getId());
	}

	@Test
	public void getAnswersBySurveyId() {
		List<Answer> surveyAnswers = surveyRepository.getAnswersBySurveyId(1);
		assertEquals(2, surveyAnswers.size());
	}

	@Test
	public void findSurveyByStatusEquals() {
		List<Survey> surveys = surveyRepository.findSurveyByStatusEquals(SurveyStatus.ONGOING);
		assertEquals(3, surveys.size());
	}




}
