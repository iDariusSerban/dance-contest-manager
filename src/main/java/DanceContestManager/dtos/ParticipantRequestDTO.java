package DanceContestManager.dtos;

import DanceContestManager.entities.DivisionType;
import DanceContestManager.entities.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParticipantRequestDTO {
    private String name;

    private String country;

    private String emailAddress;

    private RoleType role;

    private String contestName;
    private DivisionType divisionType;



}
