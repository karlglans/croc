package se.purple.croc.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UncategorizedIT extends SimpleEndpointTests {
	@Test
	public void canCreateNewForm() {
		Map<String, Map<String, Object>> result = excQuery("mutation {createForm(title: \"aaa\"){id}}");
		assertEquals(1, result.size());
		assert(result.containsKey("createForm"));
	}


	@Test
	public void canQueryFromAndQuestions() throws JsonProcessingException {
		String query = "{ form(formId: 1) { questions { id } } }";
		Map<String, Map<String, Object>> result = excQuery(query);
		assertEquals(1, result.size());
		String json = new ObjectMapper().writeValueAsString(result);
		assertEquals("{\"form\":{\"questions\":[{\"id\":\"1\"},{\"id\":\"2\"}]}}", json);
	}
}
