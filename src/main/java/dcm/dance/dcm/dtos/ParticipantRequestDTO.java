package dcm.dance.dcm.dtos;

import dcm.dance.dcm.entities.RoleType;
import jakarta.persistence.Column;

public class ParticipantRequestDTO {
    private String name;

    private String country;

    private String emailAddress;

    private RoleType role;

    public ParticipantRequestDTO(String name, String country, String emailAddress, RoleType role) {
        this.name = name;
        this.country = country;
        this.emailAddress = emailAddress;
        this.role = role;
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
}
