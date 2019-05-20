package se.purple.croc.dto;

import lombok.Data;

@Data
public class SurveyDto {
	int id;
	String name;

	// These are added to Survey
//	int countedAnsweringParticipants;
//	int countedParticipants;
	SurveyCountingSummaryDto summaryDto;
}
