package se.purple.croc.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class AnswerIT extends SimpleEndpointTests {


	// From testset: answer (survey_id, responder_id, question_id, value) VALUES (1, 3, 1, 2),
	@Test
	public void canAlterAnAnswer() throws JsonProcessingException {
		var result = excQuery("mutation { updateAnswer(surveyId: 1, userId: 3, " +
				"questionId: 1, value: 5 ){ value }}");
		String json = new ObjectMapper().writeValueAsString(result);
		String expected = "{\"updateAnswer\":{\"value\":5}}";
		assertEquals(expected, json);
	}

	@Test
	public void canCreateNewAnswer_whenRegisteredAsParticipant() throws JsonProcessingException {
		var result = excQuery("mutation { updateAnswer(surveyId: 2, userId: 3, " +
				"questionId: 1, value: 1 ){ value }}");
		String json = new ObjectMapper().writeValueAsString(result);
		String expected = "{\"updateAnswer\":{\"value\":1}}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetErrorMessage() throws JsonProcessingException {
		var result = excQuery("mutation { updateAnswer(surveyId: 100, userId: 3, " +
				"questionId: 1, value: 1 ){ value }}");
		String json = new ObjectMapper().writeValueAsString(result);
		String expected = "{\"updateAnswer\":{\"value\":1}}";
		assertEquals(expected, json);
	}
}