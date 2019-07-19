package se.purple.croc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.*;
import se.purple.croc.dto.ParticipantDto;

import java.util.List;
import java.util.Set;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {
	Survey findDistinctById(int id);

	@Query("Select a from Answer a JOIN FETCH a.survey s where s.id = 1")
	List<Answer> getAnswersBySurveyId(Integer surveyId);

	@Query("Select s from Survey s where s.status = :surveyStatus")
	List<Survey> findSurveyByStatusEquals(@Param("surveyStatus") SurveyStatus surveyStatus);


	@Query("Select sp.participant from SurveyParticipant sp JOIN sp.survey s where s.id = :surveyId")
	Set<Users> findSurveyParticipantsUsersBySurveyId(@Param("surveyId") int surveyId);

	@Query("Select new se.purple.croc.dto.ParticipantDto(sp.participant.id, s.id, sp.participant.email, " +
			"sp.complete) " +
			"from SurveyParticipant sp JOIN sp.survey s on s.id = :surveyId")
	List<ParticipantDto> findSurveyParticipantsBySurveyId(@Param("surveyId") int surveyId);


	@Query("Select sp.survey from SurveyParticipant sp JOIN sp.participant p where p.id = :participantId")
	List<Survey> findSurveyByParticipantsEquals(@Param("participantId") int participantId);

	@Query("Select count(p) from SurveyParticipant sp JOIN sp.participant p JOIN sp.survey s where s.id = :surveyId")
	int countParticipantSurvey(@Param("surveyId") int surveyId);

	@Query("Select count(p) from SurveyParticipant sp JOIN sp.participant p JOIN sp.survey s where s.id = :surveyId and sp.complete = true")
	int countAnsweringParticipantsInSurvey(@Param("surveyId") int surveyId);


	@Query("Select sp.participant from SurveyParticipant sp JOIN sp.participant p JOIN sp.survey s " +
			"where p.id = :participantId AND s.id = :surveyId")
	Users getUserInSurvey(@Param("participantId") int participantId, @Param("surveyId") int surveyId);


	// this might be written into a survey when it started instead
	@Query("Select count(fq) from Survey s JOIN s.form f JOIN f.formQuestions fq " +
			"where s.id = :surveyId")
	int getNumberOfQuestionsInSurvey(@Param("surveyId") int surveyId);


//	@Query("Select s, count(p) as counted from Survey s JOIN s.participants p where s.status = :surveyStatus  group by s ORDER BY s.id")
//	List<Object> findSurveyAndCountByStatusEquals(@Param("surveyStatus") SurveyStatus surveyStatus);



	@Query("Select s, count(a) as countedAnswers from Survey s LEFT OUTER JOIN s.answers a " +
			"where s.status = :surveyStatus group by s ORDER BY s.id")
	List<Object> findSurveyByStatusAndCountAnswers(@Param("surveyStatus") SurveyStatus surveyStatus);
}
