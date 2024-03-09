package DanceContestManager.dtos;

import DanceContestManager.entities.DivisionType;
import DanceContestManager.entities.RoleType;

public class ParticipantRequestDTO {
    private String name;

    private String country;

    private String emailAddress;

    private RoleType role;

    private String contestName;
    private DivisionType divisionType;


    public ParticipantRequestDTO(String name, String country, String emailAddress, RoleType role, String contestName, DivisionType divisionType) {
        this.name = name;
        this.country = country;
        this.emailAddress = emailAddress;
        this.role = role;
        this.contestName = contestName;
        this.divisionType = divisionType;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public DivisionType getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(DivisionType divisionType) {
        this.divisionType = divisionType;
    }
}
