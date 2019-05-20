package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import se.purple.croc.service.AuthService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SurveyTestsIT extends SimpleEndpointTests {

	@Autowired
	private TestDataGrabber testDataGrabber;

	@MockBean
	private AuthService authService;

	@Test
	public void canGetSurvey() {
		String json = excQuery("query { survey(id: 1) { id } }");
		String expected = "{\"survey\":{\"id\":\"1\"}}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetParticipants() {
		String json = excQuery("query { survey(id: 1) { participants { id } }}");
		String expected = "{\"survey\":{\"participants\":[{\"id\":\"3\"},{\"id\":\"4\"}]}}";
		assertEquals(expected, json);
	}

	@Test
	public void canCreateNewSurvey() {
		String json = excQuery("mutation {createSurvey(formId: 1, name: \"aaa\"){id}}");
		String expected = "{\"createSurvey\":{\"id\":\"5\"}}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetOngoingSurveys_whenRegularUser() {
		when(authService.getPrincipal()).thenReturn(testDataGrabber.user4);
		String json = excQuery("query { surveys { id } }");
		// these should be the ongoing surveys where the user is listed as a participant
		String expected = "{\"surveys\":[{\"id\":\"1\"},{\"id\":\"2\"}]}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetOngoingSurveys_whenSupervisor() {
		when(authService.getPrincipal()).thenReturn(testDataGrabber.user3supervisor);
		String json = excQuery("query { surveys(status:ONGOING) { id } }");
		String expected = "{\"surveys\":[{\"id\":\"1\"},{\"id\":\"2\"},{\"id\":\"3\"}]}";
		assertEquals(expected, json);
	}

	@Test
	public void canGetCountedAnsweringForOngoingSurveys() {
		when(authService.getPrincipal()).thenReturn(testDataGrabber.user3supervisor);
		String json = excQuery("query { surveys(status:ONGOING) { id summary { nbParticipants }  } }");
		String expected = "{\"surveys\":[{\"id\":\"1\",\"summary\":{\"nbParticipants\":2}},{\"id\":\"2\",\"summary\":{\"nbParticipants\":1}},{\"id\":\"3\",\"summary\":{\"nbParticipants\":3}}]}";
		assertEquals(expected, json);
	}
}
