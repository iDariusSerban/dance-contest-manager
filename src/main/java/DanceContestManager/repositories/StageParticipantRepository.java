package DanceContestManager.repositories;

import DanceContestManager.entities.Participant;
import DanceContestManager.entities.StageParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageParticipantRepository extends JpaRepository<StageParticipant, Long> {

    Optional<StageParticipant> findTopByOrderByContestNumberDesc ();

    List<StageParticipant> findAllByStageId (Long stageId);

    StageParticipant findByParticipantIdAndStageId (Long participantId , Long stageId);

}
