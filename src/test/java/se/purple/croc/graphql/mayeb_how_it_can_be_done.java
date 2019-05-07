package se.purple.croc.graphql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@Sql(scripts = "/java/se/purple/croc/service/data.sql")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//		classes = se.purple.croc.CrocApplication.class)
//@AutoConfigureMockMvc(secure=false)
//public class survey {
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	public void testGraphQL() throws Exception {
//		String payload = "{\"query\":\"{survey (id: 1) { id } }\",\"variables\":null,\"operationName\":null}";
//
//		this.mockMvc.perform(
//				post("/graphql")
//						.content(payload)
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk());
//	}
//}
