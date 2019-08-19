package se.purple.croc.graphql;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.service.AuthService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserGroupsIT extends SimpleEndpointTests {
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private TestDataGrabber testDataGrabber;

	@MockBean
	private AuthService authService;

	@Test
	public void canGetUserGroupAndUsers() throws IOException {
		String query = "{ userGroup(id: 2) { id users { id } } }";
		String jsonStringResponse = excQuery(query);
		JsonNode json = mapper.readTree(jsonStringResponse);
		// Should be something like: {"userGroup":{"id":"2","users":[{"id":"12"},{"id":"14"},{"id":"15"},{"id":"13"}]}}
		assertEquals("2", json.get("userGroup").get("id").asText());
		assertEquals(4, json.get("userGroup").get("users").size());
	}

	@Test
	public void canGetUserGroups() {
		String query = "{ userGroups { id } }";
		String json = excQuery(query);
		String expected = "{\"userGroups\":[{\"id\":\"1\"},{\"id\":\"2\"},{\"id\":\"3\"}]}";
		assertEquals(expected, json);
	}

	@WithMockUser(roles={"SUPERVISOR"})
	@Test
	public void addUserToGroup_addingUserTo2ndGroup_shouldDisplayUserInTwoGroups() throws IOException {
		String jsonStringAnswer = excQuery("mutation { addUserToGroup( userId: \"2\", userGroupId: \"2\" ){ id groups { id } }}");
		JsonNode answer = mapper.readTree(jsonStringAnswer);
		JsonNode groups = answer.get("addUserToGroup").get("groups");
		assertEquals(2, groups.size());
	}


}
