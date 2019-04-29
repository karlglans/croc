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

	// maybe should be inferred from a search from surveys
	@Column(name = "ISEDITABLE")
	private boolean isEditable;

	@OneToMany(mappedBy = "form", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private List<FormQuestion> formQuestions;

}
