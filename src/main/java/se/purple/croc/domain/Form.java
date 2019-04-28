package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;

	@OneToMany(mappedBy = "form", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<FormQuestion> formQuestions;

}
