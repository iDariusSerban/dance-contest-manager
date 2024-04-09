package DanceContestManager.repositories;

import DanceContestManager.entities.Division;
import DanceContestManager.entities.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JudgeRepository extends JpaRepository<Judge,Long> {

    Judge findJudgeByNameIs (String name);
    List<Judge> findAllByDivision (Division division);
}
