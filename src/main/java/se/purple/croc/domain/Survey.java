package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "SURVEY_PARTICIPANT",
			joinColumns = { @JoinColumn(name = "SURVEY_ID") },
			inverseJoinColumns = { @JoinColumn(name = "PARTICIPANT_ID") }
	)
	private List<Users> participants;


	@OneToMany(
			mappedBy="survey",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Answer> answers;


}
