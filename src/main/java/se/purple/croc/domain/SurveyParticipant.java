package se.purple.croc.domain;

import lombok.Data;

import javax.persistence.*;

// pattern from:
// https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Mapping_a_Join_Table_with_Additional_Columns

@Data
@Entity
@IdClass( SurveyParticipantId.class )
public class SurveyParticipant {

	@Id
	private int surveyId;

	@Id
	private int participantId;

	@ManyToOne
//	@PrimaryKeyJoinColumn(name="SURVEYID", referencedColumnName="ID")
	@JoinColumn(name = "surveyId", updatable = false, insertable = false)
	private Survey survey;

	@ManyToOne
	@JoinColumn(name = "participantId", updatable = false, insertable = false)
	private Users participant;

	private boolean complete = false;

	public void setSurvey(Survey survey) {
		this.setSurveyId(survey.getId());
		this.survey = survey;
	}

	public void setParticipant(Users participant) {
		this.setParticipantId(participant.getId());
		this.participant = participant;
	}
}
