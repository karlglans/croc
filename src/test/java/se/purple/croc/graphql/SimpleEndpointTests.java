package se.purple.croc.graphql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) // BEFORE_EACH_TEST_METHOD
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = se.purple.croc.CrocApplication.class)
public class SimpleEndpointTests {
	@Autowired
	GraphQLSchema schema;


	protected String excQuery(String query) {
		GraphQL graphQL = GraphQL.newGraphQL(schema)
				.build();

		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
				.query(query)
				.build();

		ExecutionResult executionResult = graphQL.execute(executionInput);

		List<GraphQLError> errors = executionResult.getErrors();
		assertEquals(0, errors.size());

		var result = executionResult.getData();

		try {
			return new ObjectMapper().writeValueAsString(result);
		} catch (JsonProcessingException ignored) {}
		assert(false);
		return "";
	}

}
