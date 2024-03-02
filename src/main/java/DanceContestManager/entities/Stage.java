package DanceContestManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private StageType stageType;
    @ManyToOne
    @JsonBackReference("division-stage")
    @JoinColumn(name = "division_id")
    private Division division;
    @OneToMany(mappedBy = "stage", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("stage-grade")
    private List<Grade> gradeList;

    @OneToMany(mappedBy = "stage", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("stage-stageParticipant")
    private List<StageParticipant> stageParticipantList;

    public Stage() {
    }

    public Stage(StageType stageType, Division division) {
        this.stageType = stageType;
        this.division = division;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StageType getStageType() {
        return stageType;
    }

    public void setStageType(StageType stageType) {
        this.stageType = stageType;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
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
