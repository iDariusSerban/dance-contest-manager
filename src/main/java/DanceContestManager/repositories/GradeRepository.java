package DanceContestManager.repositories;

import DanceContestManager.entities.Grade;
import DanceContestManager.entities.Judge;
import DanceContestManager.entities.StageParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Long> {
    List<Grade> findAllByJudge (Judge judge);

    List<Grade> findAllByStageParticipant (StageParticipant stageParticipant);
}
