package se.purple.croc.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UserGroupsIT extends SimpleEndpointTests {
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void canGetUserGroupAndUsers() {
		String query = "{ userGroup(id: 2) { id users { id } } }";
		String json = excQuery(query);
		String expected = "{\"userGroup\":{\"id\":\"2\",\"users\":[{\"id\":\"12\"},{\"id\":\"13\"},{\"id\":\"14\"},{\"id\":\"15\"}]}}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetUserGroups() {
		String query = "{ userGroups { id } }";
		String json = excQuery(query);
		String expected = "{\"userGroups\":[{\"id\":\"1\"},{\"id\":\"2\"},{\"id\":\"3\"}]}";
		assertEquals(expected, json);
	}

	@Test
	public void addUserToGroup_addingUserTo2ndGroup_shouldDisplayUserInTwoGroups() throws IOException {
		String jsonStringAnswer = excQuery("mutation { addUserToGroup( userId: \"2\", userGroupId: \"2\" ){ id groups { id } }}");
		JsonNode answer = mapper.readTree(jsonStringAnswer);
		JsonNode groups = answer.get("addUserToGroup").get("groups");
		assertEquals(2, groups.size());
	}


}
