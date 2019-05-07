package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Form {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;

	// maybe should be inferred from a search from surveys
	@Column(name = "ISEDITABLE")
	private boolean isEditable;

//	@OneToMany(mappedBy = "form", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OneToMany(mappedBy = "form")
	private List<FormQuestion> formQuestions;

	@OneToMany(mappedBy = "form")
	private Set<Survey> surveys;

}
