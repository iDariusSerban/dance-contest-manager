package DanceContestManager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String country;
    @Column
    private String emailAddress;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Column
    private String contestNumber;

    @OneToMany(mappedBy = "participant", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("participant-grade")
    private List<Grade> gradeList;
    @OneToMany(mappedBy = "participant", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("participant-stageParticipant")
    private List<StageParticipant> stageParticipantList;

    public Participant() {
        this.stageParticipantList = new ArrayList<>();
        this.gradeList = new ArrayList<>();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAdress) {
        this.emailAddress = emailAdress;
    }

    public String getContestNumber() {
        return contestNumber;
    }

    public void setContestNumber(String coupleNumber) {
        this.contestNumber = coupleNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }


    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public List<StageParticipant> getStageParticipantList() {
        return stageParticipantList;
    }

    public void setStageParticipantList(List<StageParticipant> stageParticipantList) {
        this.stageParticipantList = stageParticipantList;
    }
}