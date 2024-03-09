package DanceContestManager.repositories;

import DanceContestManager.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {

    Participant findParticipantById (Long id);
}
