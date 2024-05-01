package DanceContestManager.dtos;

import DanceContestManager.entities.DivisionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JudgeAssignRequestDTO {
    private Long judge_id;
    private String contestName;
    private DivisionType divisionType;


}
