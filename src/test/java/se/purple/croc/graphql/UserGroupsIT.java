package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class UserGroupsIT extends SimpleEndpointTests {
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
}
