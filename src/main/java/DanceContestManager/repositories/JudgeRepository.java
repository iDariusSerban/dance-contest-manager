package DanceContestManager.repositories;

import DanceContestManager.entities.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeRepository extends JpaRepository<Judge,Long> {
}
