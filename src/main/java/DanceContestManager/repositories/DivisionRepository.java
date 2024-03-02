package DanceContestManager.repositories;

import DanceContestManager.entities.Contest;
import DanceContestManager.entities.Division;
import DanceContestManager.entities.DivisionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
    Division findDivisionByDivisionTypeAndContest (DivisionType divisionType, Contest contest);
}
