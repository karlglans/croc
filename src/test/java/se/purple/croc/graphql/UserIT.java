package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UserIT extends SimpleEndpointTests {
	@Test
	public void canGetSingleUserAndGroups() {
		String query = "{ user(id: 12) { id email groups { id name } } }";
		String json = excQuery(query);
		String expected = "{\"user\":{\"id\":\"12\",\"email\":\"employee9@purple.com\",\"groups\":[" +
				"{\"id\":\"1\",\"name\":\"Everyone from A\"},{\"id\":\"2\",\"name\":\"Subset of A and B\"}]}}";
		assertEquals(expected, json);
	}
}
