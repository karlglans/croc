package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Survey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	private Users creator;

	@ManyToOne
	private Form form;

	private String name = "aaaa";

	@Enumerated(EnumType.STRING)
	private SurveyStatus status;

//	@ManyToMany(cascade = { CascadeType.MERGE })
	@ManyToMany
	@JoinTable(
			name = "SURVEY_PARTICIPANT",
			joinColumns = { @JoinColumn(name = "SURVEY_ID") },
			inverseJoinColumns = { @JoinColumn(name = "PARTICIPANT_ID"), },
			uniqueConstraints = {@UniqueConstraint(
					columnNames = {"SURVEY_ID", "PARTICIPANT_ID"})}
	)
	private Set<Users> participants = new HashSet<>();
//	private List<Users> participants;


	@OneToMany(
			mappedBy="survey",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Answer> answers;


}
