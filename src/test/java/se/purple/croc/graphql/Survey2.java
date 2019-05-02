package se.purple.croc.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = "/testdata/data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = se.purple.croc.CrocApplication.class)

public class Survey2 {

	@Autowired
	GraphQLSchema schema;

	private Map<String, Map<String, Object>> excQuery(String query) {
		GraphQL graphQL = GraphQL.newGraphQL(schema)
				.build();

		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
				.query(query)
				.build();

		ExecutionResult executionResult = graphQL.execute(executionInput);

		List<GraphQLError> errors = executionResult.getErrors();
		assertEquals(0, errors.size());

		return (Map<String, Map<String, Object>>) executionResult.getData();
	}

	@Test
	public void canGetSurvey() {
		Map<String, Map<String, Object>> result = excQuery("query { survey(id: 1) { id } }");
		assertEquals(1, result.size());
		assert(result.containsKey("survey"));
	}

	@Transactional
	@Test
	public void canCreateNewForm() {
		Map<String, Map<String, Object>> result = excQuery("mutation {createForm(title: \"aaa\"){id}}");
		assertEquals(1, result.size());
		assert(result.containsKey("createForm"));
	}

	@Transactional
	@Test
	public void canQueryFromAndQuestions() throws JsonProcessingException {
		System.out.println("    starting test");
		String query = "{ form(formId: 1) { questions { id } } }";
		Map<String, Map<String, Object>> result = excQuery(query);
		assertEquals(1, result.size());
		String json = new ObjectMapper().writeValueAsString(result);
		assertEquals("{\"form\":{\"questions\":[{\"id\":\"1\"},{\"id\":\"2\"}]}}", json);
	}

	@Transactional
	@Test
	public void canGetUserGroupsAndUsers() throws JsonProcessingException {
		String query = "{ userGroups { id users { id } } }";
		Map<String, Map<String, Object>> result = excQuery(query);
		assertEquals(1, result.size());
		String json = new ObjectMapper().writeValueAsString(result);
		String expected = "{\"userGroups\":[{\"id\":\"1\",\"users\":[{\"id\":\"2\"},{\"id\":\"3\"}]},"
			+ "{\"id\":\"2\",\"users\":[{\"id\":\"2\"}]},{\"id\":\"3\",\"users\":[]}]}";
		assertEquals(expected, json);
	}
}
