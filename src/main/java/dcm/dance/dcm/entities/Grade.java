package dcm.dance.dcm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private int gradeValue;
    @ManyToOne
    @JsonBackReference("judge-grade")
    @JoinColumn(name = "judge_id")
    private Judge judge;

    @ManyToOne
    @JsonBackReference("stage-grade")
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne
    @JsonBackReference("participant-grade")
    @JoinColumn(name = "participant_id")
    private Participant participant;

    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
