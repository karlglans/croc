package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class QuestionIT extends SimpleEndpointTests {

//	@Test
//	public void canAddQuestion() {
//		String json = excQuery("mutation { addQuestion( input: {text: \"aaa\"} ){ id }}");
//		String expected = "{\"addQuestion\":{\"id\":\"9\"}}";
//		assertEquals(expected, json);
//	}

	@Test
	public void canAlterQuestion() {
		String json = excQuery("mutation { updateQuestion( input: {text: \"aaa\", id: 3} ){ id questions { id text } }}");
		String expected = "{\"updateQuestion\":{\"id\":\"2\"," +
				"\"questions\":[{\"id\":\"2\",\"text\":\"question2 form1 and form2\"}," +
				"{\"id\":\"3\",\"text\":\"aaa\"}]}}";
		assertEquals(expected, json);
	}

//	@l
}
