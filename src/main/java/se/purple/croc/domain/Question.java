package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String text;

	@Enumerated(EnumType.STRING)
	private QuestionType questionType = QuestionType.NUMERIC;


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
