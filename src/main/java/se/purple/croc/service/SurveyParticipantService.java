package se.purple.croc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.purple.croc.domain.SurveyParticipant;
import se.purple.croc.domain.SurveyParticipantId;
import se.purple.croc.domain.Users;
import se.purple.croc.dto.ParticipantDto;
import se.purple.croc.dto.SurveyDto;
import se.purple.croc.repository.AnswerRepository;
import se.purple.croc.repository.SurveyParticipantRepository;
import se.purple.croc.repository.SurveyRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SurveyParticipantService {

	@Autowired
	SurveyRepository surveyRepo;

	@Autowired
	SurveyParticipantRepository surveyParticipantRepo;

	@Autowired
	AnswerRepository answerRepo;

	Set<Users> getParticipantsBySurvey(int surveyId) {
		return surveyRepo.findSurveyParticipantsUsersBySurveyId(surveyId);
	}

	ParticipantDto makeParticipantDto(int surveyId, Users user) {
		return new ParticipantDto(user.getId(), surveyId, user.getEmail(), false);
	}

	public List<ParticipantDto> getParticipants(SurveyDto survey){
		return surveyRepo.findSurveyParticipantsBySurveyId(survey.getId());
	}

	void updateSurveyCompleteStatus(int surveyId, int participantId) {
		// could be optimized
		int nbAnswers = answerRepo.countSurveyAnswersForResponder(surveyId, participantId);
		int nbQuestions = surveyRepo.getNumberOfQuestionsInSurvey(surveyId);
		boolean hasCompletedSurvey = nbAnswers == nbQuestions;
		if (hasCompletedSurvey) {
			SurveyParticipantId surveyParticipantId = new SurveyParticipantId();
			surveyParticipantId.setSurveyId(surveyId);
			surveyParticipantId.setParticipantId(participantId);
			Optional<SurveyParticipant> surveyParticipant = surveyParticipantRepo.findById(surveyParticipantId);
			if (surveyParticipant.isPresent()){
				surveyParticipant.get().setComplete(true);
			}
		}
	}
}
