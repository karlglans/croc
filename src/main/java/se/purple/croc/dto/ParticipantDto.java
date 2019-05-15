package se.purple.croc.dto;

import lombok.Data;
import se.purple.croc.domain.Answer;
import se.purple.croc.domain.Survey;
import se.purple.croc.domain.Users;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParticipantDto {
	private int id;
	private int surveyId;
	private String email;
	List<AnswerDto> answers = new ArrayList<>();
}
