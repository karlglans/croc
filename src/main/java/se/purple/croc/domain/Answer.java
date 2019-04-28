package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@IdClass( AnswerIdentity.class )
public class Answer {
	@Id
	@ManyToOne
	private Question question;

	@Id
	@ManyToOne
	private Users responder;

	@Id
	@ManyToOne
	private Survey survey;

	private Integer value;
}
