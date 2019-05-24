package se.purple.croc.service;

import lombok.var;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyParticipantService {

	@Autowired
	SurveyRepository surveyRepo;

	@Autowired
	SurveyParticipantRepository surveyParticipantRepo;

	@Autowired
	AnswerRepository answerRepo;

	List<Users> getParticipantsBySurvey(int surveyId) {
		return surveyRepo.findSurveyParticipantsUsersBySurveyId(surveyId);
	}

	ParticipantDto makeParticipantDto(int surveyId, Users user) {
		ParticipantDto participant = new ParticipantDto();
		participant.setSurveyId(surveyId);
		participant.setId(user.getId());
		participant.setEmail(user.getEmail());
		return participant;
	}

	public List<ParticipantDto> getParticipants(SurveyDto survey){
		final int surveyId = survey.getId();
		List<ParticipantDto> participantDtoList = new ArrayList<>();
		var users = getParticipantsBySurvey(surveyId);
		for(Users user : users) {
			participantDtoList.add(makeParticipantDto(surveyId, user));
		}
		return participantDtoList;
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
