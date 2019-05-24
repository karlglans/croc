package se.purple.croc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class SurveyParticipantId implements Serializable {
	private int surveyId;
	private int participantId;

	public int hashCode() {
		return (surveyId + participantId);
	}

	public boolean equals(Object object) {
		if (object instanceof SurveyParticipant) {
			SurveyParticipantId otherId = (SurveyParticipantId) object;
			return (otherId.surveyId == this.surveyId) && (otherId.participantId == this.participantId);
		}
		return false;
	}
}
