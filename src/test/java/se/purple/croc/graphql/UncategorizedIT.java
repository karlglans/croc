package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UncategorizedIT extends SimpleEndpointTests {
	@Test
	public void swapQuestionOnForm() {
		String query = "mutation { swapQuestionOnForm(formId: 1, questionId: 1, destSpotNumber: 2) { questions { id } } }";
		String json = excQuery(query);
		assertEquals("{\"swapQuestionOnForm\":{\"questions\":[{\"id\":\"2\"},{\"id\":\"1\"}]}}", json);
	}
}
