package se.purple.croc.dto;

import lombok.Data;
import se.purple.croc.domain.QuestionType;

@Data
public class InputQuestionDto {
	private String text;
	private Integer id;
	private QuestionType questionType;
}
