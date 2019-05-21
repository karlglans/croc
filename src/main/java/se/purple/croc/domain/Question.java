package se.purple.croc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String text;


//	@OneToOne(mappedBy="question")
//	public FormQuestion formQuestion; // new


//	@JsonIgnore
//	@ManyToMany(cascade = { CascadeType.MERGE })
//	@JoinTable(
//			name = "GROUP_QUESTION",
//			joinColumns = { @JoinColumn(name = "question_id") },
//			inverseJoinColumns = { @JoinColumn(name = "FORM_QUESTION_GROUP_ID") }
//	)
//	private Set<FormQuestionGroup> formQuestionGroups = new HashSet<>();
}
