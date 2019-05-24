package se.purple.croc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.purple.croc.domain.SurveyParticipant;
import se.purple.croc.domain.SurveyParticipantId;

@Repository
public interface SurveyParticipantRepository extends CrudRepository<SurveyParticipant, SurveyParticipantId> {
}
