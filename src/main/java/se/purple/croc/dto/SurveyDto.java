package se.purple.croc.dto;

import lombok.Data;

@Data
public class SurveyDto {
	int id;
	String name;

	// Should be only for supervisors
	SurveyCountingSummaryDto summaryDto;

	// Should only be for participating users
	OwnSurveyStatusDto ownStatus;
}
