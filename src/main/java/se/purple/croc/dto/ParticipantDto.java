package se.purple.croc.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParticipantDto {
	private int id;
	private int surveyId;
	private String email;
	List<AnswerDto> answers = new ArrayList<>();
	private boolean isComplete;
	public ParticipantDto(int id, int surveyId, String email, boolean isComplete) {
		this.id = id;
		this.surveyId = surveyId;
		this.email = email;
		this.isComplete = isComplete;
	}
}
