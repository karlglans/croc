package se.purple.croc.repository;

import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.domain.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
// @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DataJpaTest
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
		assertEquals(6, survey1.getId());
	}

	@Test
	public void getAnswersBySurveyId() {
		List<Answer> surveyAnswers = surveyRepository.getAnswersBySurveyId(1);
		assertEquals(5, surveyAnswers.size());
	}

	@Test
	public void findSurveyByStatusEquals() {
		List<Survey> surveys = surveyRepository.findSurveyByStatusEquals(SurveyStatus.ONGOING);
		assertEquals(4, surveys.size());
	}

	@Test
	public void findSurveyParticipantsUsersBySurveyId() {
		List<Users> users = surveyRepository.findSurveyParticipantsUsersBySurveyId(1);
		assertEquals(3, users.size());
	}



//	@Test
//	public void findSurveyByStatusAndParticipantId_canFindMultipleSurveys() {
//		List<Survey> surveysWhereUserShouldBeIn = surveyRepository.findSurveyByStatusAndParticipantId(SurveyStatus.ONGOING, 4);
//		assertEquals(2, surveysWhereUserShouldBeIn.size());
//	}
//
//	@Test
//	public void findSurveyByStatusEqualsAndParticipants_canGiveAEmptyResult() {
//		List<Survey> surveysWhereUserShouldBeIn = surveyRepository.findSurveyByStatusAndParticipantId(SurveyStatus.ONGOING, 1000);
//		assertEquals(0, surveysWhereUserShouldBeIn.size());
//	}

	@Test
	public void findSurveyByParticipantsEquals() {
		var surveys = surveyRepository.findSurveyByParticipantsEquals(4);
		assertEquals(3, surveys.size());
	}

	@Test
	public void canCountSurveyParticipants() {
		int count = surveyRepository.countParticipantSurvey(3);
		assertEquals(3, count);
	}


	@Test
	public void getUserInSurvey() {
		Users user  = surveyRepository.getUserInSurvey(4, 1);
		assertNotNull(user);
	}

	@Test
	public void getNumberOfQuestionsInSurvey() {
		int nbQuestions  = surveyRepository.getNumberOfQuestionsInSurvey(1);
		assertEquals(2, nbQuestions);
	}


//	@Test
//	public void findSurveyAndCountByStatusEquals() {
//		List<Object> result = surveyRepository.findSurveyAndCountByStatusEquals(SurveyStatus.ONGOING);
//
//		// expecting 3 Surveys
//		assertEquals(3, result.size());
//		Object[] Survey1Pair = (Object[]) result.get(0);
//		Object[] Survey2Pair = (Object[]) result.get(1);
//		Object[] Survey3Pair = (Object[]) result.get(2);
//
//		assertEquals(2, Survey1Pair.length);
//		assertEquals(1, ((Survey) Survey1Pair[0]).getId() );
//		assertEquals(2, (long)Survey1Pair[1] );
//
//		assertEquals(2, Survey2Pair.length);
//		assertEquals(2, ((Survey) Survey2Pair[0]).getId() );
//		assertEquals(1, (long)Survey2Pair[1] );
//
//		assertEquals(2, Survey3Pair.length);
//		assertEquals(3, ((Survey) Survey3Pair[0]).getId() );
//		assertEquals(3, (long)Survey3Pair[1] );
//	}


	@Test
	public void findSurveyByStatusAndCountAnswers() {
		List<Object> result = surveyRepository.findSurveyByStatusAndCountAnswers(SurveyStatus.ONGOING);

		// expecting 4 Surveys
		assertEquals(4, result.size());
		Object[] Survey1Pair = (Object[]) result.get(0);
		Object[] Survey2Pair = (Object[]) result.get(1);
		Object[] Survey3Pair = (Object[]) result.get(2);

		assertEquals(2, Survey1Pair.length);
		assertEquals(1, ((Survey) Survey1Pair[0]).getId() );
		assertEquals(5, (long)Survey1Pair[1] ); // answers

		assertEquals(2, Survey2Pair.length);
		assertEquals(2, ((Survey) Survey2Pair[0]).getId() );
		assertEquals(0, (long)Survey2Pair[1] ); // answers

		assertEquals(2, Survey3Pair.length);
		assertEquals(3, ((Survey) Survey3Pair[0]).getId() );
		assertEquals(6, (long)Survey3Pair[1] ); // answers
	}
}
