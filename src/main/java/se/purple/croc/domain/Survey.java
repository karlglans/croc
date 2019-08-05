package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Survey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Users creator;

	@ManyToOne(fetch = FetchType.LAZY)
	private Form form;

	private String name = "noname";

	@Enumerated(EnumType.STRING)
	private SurveyStatus status = SurveyStatus.IN_CREATION;

	// to be updated when a participant store an answer
	private int countedAnsweringParticipants;

	// TODO, this relation is still not working properly
	@OneToMany(mappedBy="participant")
	private List<SurveyParticipant> participants = new ArrayList<>();

	@OneToMany(
			mappedBy="survey",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Answer> answers;

	private Timestamp startAt;
	private Timestamp endAt;

}
