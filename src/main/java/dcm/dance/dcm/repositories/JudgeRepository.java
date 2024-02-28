package dcm.dance.dcm.repositories;

import dcm.dance.dcm.entities.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeRepository extends JpaRepository<Judge,Long> {
}
