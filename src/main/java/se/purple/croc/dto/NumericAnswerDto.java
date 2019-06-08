package se.purple.croc.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NumericAnswerDto extends AnswersSummary {

	private int[] count = {0, 0, 0, 0, 0, 0};

	// NOTE: this solution is a bit brittle. The FE-client has to have knowledge about this type.
	// How answers is handled is handled by FE is based on its question type.
	public void makeContent() {
		try {
			this.setContent(String.format("{\"count\":%s}", new ObjectMapper().writeValueAsString(count)));
		} catch (JsonProcessingException ignored) {
			this.setContent("failed");
		}
	}
}
