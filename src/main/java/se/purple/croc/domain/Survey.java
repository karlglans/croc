package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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

	@ManyToOne(fetch = FetchType.LAZY)
	private Users creator;

	@ManyToOne(fetch = FetchType.LAZY)
	private Form form;

	private String name = "noname";

	@Enumerated(EnumType.STRING)
	private SurveyStatus status = SurveyStatus.IN_CREATION;

	// to be updated when a participant store an answer
	private int countedAnsweringParticipants;


//	@ManyToMany
//	@JoinTable(
//			name = "SURVEY_PARTICIPANT",
//			joinColumns = { @JoinColumn(name = "SURVEY_ID") },
//			inverseJoinColumns = { @JoinColumn(name = "PARTICIPANT_ID"), },
//			uniqueConstraints = {@UniqueConstraint(
//					columnNames = {"SURVEY_ID", "PARTICIPANT_ID"})}
//	)
//	private List<Users> participants = new ArrayList<>();

//	@OneToMany
//	@JoinTable(
//			name = "SURVEY_PARTICIPANT",
//			joinColumns = { @JoinColumn(name = "SURVEY_ID") },
//			inverseJoinColumns = { @JoinColumn(name = "PARTICIPANT_ID"), },
//			uniqueConstraints = {@UniqueConstraint(
//					columnNames = {"SURVEY_ID", "PARTICIPANT_ID"})}
//	)
	@OneToMany(mappedBy="participant")
	private List<SurveyParticipant> participants = new ArrayList<>();

//	@ManyToMany
//	@JoinTable(
//			name = "SURVEY_PARTICIPANT2")
////	@JoinTable(
////			name = "SURVEY_PARTICIPANT2",
////			joinColumns = { @JoinColumn(name = "SURVEY_ID") },
////			inverseJoinColumns = { @JoinColumn(name = "PARTICIPANT_ID"), }
////	)
//	private List<SurveyParticipant2> participants2 = new ArrayList<>();


//	@OneToMany(mappedBy="participant")
//	private List<SurveyParticipant2> participant2;

	@OneToMany(
			mappedBy="survey",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Answer> answers;

}
