package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.service.AuthService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AnswerIT extends SimpleEndpointTests {

	@Autowired
	private TestDataGrabber testDataGrabber;

	@MockBean
	private AuthService authService;


	// From testset: answer (survey_id, responder_id, question_id, value) VALUES (1, 4, 1, 2),
	@Test
	public void canAlterAnAnswer() {
		when(authService.getPrincipal()).thenReturn(testDataGrabber.user4);
		String json = excQuery("mutation { updateAnswer(surveyId: 1, userId: 4, " +
				"questionId: 1, value: 5 ){ value }}");
		String expected = "{\"updateAnswer\":{\"value\":5}}";
		assertEquals(expected, json);
	}

	@Test
	public void canCreateNewAnswer_whenRegisteredAsParticipant() {
		when(authService.getPrincipal()).thenReturn(testDataGrabber.user4);
		String json = excQuery("mutation { updateAnswer(surveyId: 2, userId: 4, " +
				"questionId: 1, value: 1 ){ value }}");
		String expected = "{\"updateAnswer\":{\"value\":1}}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetSurveyAnswers_whenRegisteredAsParticipant() {
		when(authService.getPrincipal()).thenReturn(testDataGrabber.user3supervisor);
		String json = excQuery("{\n" +
				"    survey(id: 1) {\n" +
				"      id\n" +
				"      name\n" +
				"      answersSum {\n" +
				"        questionId\n" +
				"        content\n" +
				"      }\n" +
				"      form {\n" +
				"        id\n" +
				"        title\n" +
				"        questions {\n" +
				"          id\n" +
				"          text\n" +
				"          questionType\n" +
				"        }\n" +
				"      }\n" +
				"    }\n" +
				"  }");
		String expected = "{\"survey\":{\"id\":\"1\",\"name\":\"Det stora vårforfoluläret 2019\",\"answersSum\":[" +
				"{\"questionId\":1,\"content\":\"{\\\"count\\\":[0,1,0,0,1,0]}\"}," +
				"{\"questionId\":2,\"content\":\"{\\\"count\\\":[0,0,1,0,0,0]}\"}]," +
				"\"form\":{\"id\":\"1\",\"title\":\"form1 ongoing survey\"," +
				"\"questions\":[" +
				"{\"id\":\"1\",\"text\":\"question1 from1\",\"questionType\":\"NUMERIC\"}," +
				"{\"id\":\"2\",\"text\":\"question2 form1 and form2\",\"questionType\":\"NUMERIC\"}]}}}";
		assertEquals(expected, json);
	}
}