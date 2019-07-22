package se.purple.croc.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class UserIT extends SimpleEndpointTests {

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void getSingleUserAndGroups_whenUserIsInTwoGroups_shouldGiveTwoGroups() throws IOException {
		String query = "{ user(id: 12) { id email groups { id name } } }";
		String jsonStringResponse = excQuery(query);
		JsonNode json = mapper.readTree(jsonStringResponse);
		JsonNode id = json.get("user").get("id");
		assertEquals("\"12\"", id.toString());
		JsonNode groups = json.get("user").get("groups");
		assertEquals(2, groups.size());
		assertTrue(groups.get(0).has("name"));
	}
}
