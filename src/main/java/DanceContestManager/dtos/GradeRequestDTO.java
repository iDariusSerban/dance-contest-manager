package DanceContestManager.dtos;

import DanceContestManager.entities.Judge;
import DanceContestManager.entities.Participant;
import DanceContestManager.entities.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GradeRequestDTO {
    private Long grade_id;
    private Double gradeValue;
    private String judgeName;
    private Long stageParticipantId;


}
