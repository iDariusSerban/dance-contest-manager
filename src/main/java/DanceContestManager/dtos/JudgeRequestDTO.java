package DanceContestManager.dtos;

import DanceContestManager.entities.DivisionType;

public class JudgeRequestDTO {
    private String name;
    private String contestName;
    private DivisionType divisionType;

    public JudgeRequestDTO(String name, String contestName, DivisionType divisionType) {
        this.name = name;
        this.contestName = contestName;
        this.divisionType = divisionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public DivisionType getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(DivisionType divisionType) {
        this.divisionType = divisionType;
    }
}
