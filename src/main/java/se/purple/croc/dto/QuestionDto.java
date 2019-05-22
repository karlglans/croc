package se.purple.croc.dto;

import lombok.Data;
import se.purple.croc.domain.FormQuestion;
import se.purple.croc.domain.Question;

@Data
public class QuestionDto {
	private int id;
	private String text;
//	private int number;
	private QuestionUserPrivileges privileges;

	public void copy(Question question) {
		this.id = question.getId();
		this.text = question.getText();
	}
}
