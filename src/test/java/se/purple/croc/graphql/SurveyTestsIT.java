package se.purple.croc.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class SurveyTestsIT extends SimpleEndpointTests {
	@Test
	public void canGetSurvey() {
		Map<String, Map<String, Object>> result = excQuery("query { survey(id: 1) { id } }");
		assertEquals(1, result.size());
		assert(result.containsKey("survey"));
	}

	@Test
	public void canGetParticipants() throws JsonProcessingException {
		Map<String, Map<String, Object>> result = excQuery("query { survey(id: 1) { participants { id } }}");
		assertEquals(1, result.size());
		String json = new ObjectMapper().writeValueAsString(result);
		String expected = "{\"survey\":{\"participants\":[{\"id\":\"3\"},{\"id\":\"4\"}]}}";
		assertEquals(expected, json);
	}
}
