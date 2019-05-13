package se.purple.croc.dto;

import lombok.Data;

@Data
public class AnswerDto {
	int participantId;
	int questionId;
	int value;
}
