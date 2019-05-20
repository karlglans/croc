package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UncategorizedIT extends SimpleEndpointTests {
	@Test
	public void canCreateNewForm() {
		String json = excQuery("mutation {createForm(title: \"aaa\"){id}}");
		assertEquals("{\"createForm\":{\"id\":\"4\"}}", json);
	}

}
