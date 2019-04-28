package se.purple.croc.dto;

import lombok.Data;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.Survey;
import se.purple.croc.domain.Users;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParticipantDto {
	private int userId;
	private int surveyId;
	List<Answer> answers = new ArrayList<>();
	public void populate(Users user, Survey survey) {
		this.userId = user.getId();
		this.surveyId = survey.getId();
	}
}
