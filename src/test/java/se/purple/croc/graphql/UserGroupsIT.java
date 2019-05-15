package se.purple.croc.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UserGroupsIT extends SimpleEndpointTests {
	@Test
	public void canGetUserGroupAndUsers() throws JsonProcessingException {
		String query = "{ userGroup(id: 2) { id users { id } } }";
		Map<String, Map<String, Object>> result = excQuery(query);
		assertEquals(1, result.size());
		String json = new ObjectMapper().writeValueAsString(result);
		String expected = "{\"userGroup\":{\"id\":\"2\",\"users\":[{\"id\":\"12\"},{\"id\":\"13\"},{\"id\":\"14\"},{\"id\":\"15\"}]}}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetUserGroups() throws JsonProcessingException {
		String query = "{ userGroups { id } }";
		Map<String, Map<String, Object>> result = excQuery(query);
		assertEquals(1, result.size());
		String json = new ObjectMapper().writeValueAsString(result);
		String expected = "{\"userGroups\":[{\"id\":\"1\"},{\"id\":\"2\"},{\"id\":\"3\"}]}";
		assertEquals(expected, json);
	}
}
