package DanceContestManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponseDTO {

    private Long gradeId;
    private Double gradeValue;
    private Long judgeId;
    private Long ParticipantId;
    private Long DivisionId;
    private Long StageId;
}
