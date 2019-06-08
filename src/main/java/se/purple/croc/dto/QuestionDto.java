package se.purple.croc.dto;

import lombok.Data;
import se.purple.croc.domain.Question;
import se.purple.croc.domain.QuestionType;

@Data
public class QuestionDto {
	private int id;
	private String text;
	private QuestionUserPrivileges privileges;
	private QuestionType questionType = QuestionType.NUMERIC;

	public void copy(Question question) {
		this.id = question.getId();
		this.text = question.getText();
	}
}
