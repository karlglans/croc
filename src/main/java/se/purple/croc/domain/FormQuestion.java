package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FormQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Form form;

	@ManyToOne
	private Question question;

	private int number;
}
