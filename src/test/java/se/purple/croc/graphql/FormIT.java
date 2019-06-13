package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class FormIT extends SimpleEndpointTests {

	@Test
	public void canQueryFromAndQuestions() {
		String query = "{ form(formId: 2) { questions { id } } }";
		String json = excQuery(query);
		assertEquals("{\"form\":{\"questions\":[{\"id\":\"2\"},{\"id\":\"3\"}]}}", json);
	}

	@Test
	public void canQueryQuestionPrivileges() {
		String query = "{ form(formId: 2) { questions { id privileges { isEditable } } } }";
		String json = excQuery(query);
		assertEquals("{\"form\":{\"questions\":[{\"id\":\"2\",\"privileges\":{\"isEditable\":false}},{\"id\":\"3\",\"privileges\":{\"isEditable\":true}}]}}", json);
	}

	@Test
	public void canRemoveQuestionFromForm() {
		String query = "mutation { removeQuestionFromForm(questionId: 6, formId: 4) { questions { id } } }";
		String json = excQuery(query);
		assertEquals("{\"removeQuestionFromForm\":{\"questions\":" +
				"[{\"id\":\"5\"},{\"id\":\"7\"},{\"id\":\"8\"}]}}", json);
	}


}
