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
    private int gradeValue;
    private Long participantID;
     private String judgeName;
     private Long stageId;


  }
