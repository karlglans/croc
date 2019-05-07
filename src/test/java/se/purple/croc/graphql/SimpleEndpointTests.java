package se.purple.croc.graphql;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Sql(scripts = "/testdata/data.sql")
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = se.purple.croc.CrocApplication.class)
public class SimpleEndpointTests {
	@Autowired
	GraphQLSchema schema;

	protected Map<String, Map<String, Object>> excQuery(String query) {
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
}
