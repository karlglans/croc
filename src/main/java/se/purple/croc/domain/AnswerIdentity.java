package se.purple.croc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class AnswerIdentity implements Serializable {
	private Integer question;
	private Integer responder;
	private Integer survey;

	public void setAnswerId(Answer answer) {
		this.question = answer.getQuestion().getId();
		this.responder = answer.getResponder().getId();
		this.survey = answer.getSurvey().getId();
	}
}
