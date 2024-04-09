package DanceContestManager.dtos;

import DanceContestManager.entities.DivisionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JudgeAlocRequestDTO {
    private String name;
    private String contestName;
    private DivisionType divisionType;


}
