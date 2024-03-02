package DanceContestManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class StageParticipant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonBackReference("participant-stageParticipant")
    @JoinColumn(name = "participant_id")
    private Participant participant;
    @ManyToOne
    @JsonBackReference("stage-stageParticipant")
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Column
    private boolean checkedIn;

    public StageParticipant() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
