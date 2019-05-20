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
}