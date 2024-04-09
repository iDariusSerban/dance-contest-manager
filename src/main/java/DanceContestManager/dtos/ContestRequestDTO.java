package DanceContestManager.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContestRequestDTO {
    private String name;
    private String location;

}
