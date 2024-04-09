package DanceContestManager.repositories;

import DanceContestManager.entities.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest,Long> {
   Contest findContestByName(String name);


}
