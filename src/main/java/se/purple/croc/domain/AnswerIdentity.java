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

//	public boolean equals(Object object) {
//		if (object instanceof Answer) {
//			Answer otherId = (Answer) object;
//			return (otherId.getQuestion().getId() == this.question.intValue()
//					&& otherId.getSurvey().getId() == this.survey.intValue()
//					&& otherId.getResponder().getId() == this.responder.intValue());
//		}
//		return false;
//	}
}
