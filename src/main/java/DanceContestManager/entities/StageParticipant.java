package DanceContestManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StageParticipant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer contestNumber;

    @ManyToOne
    @JsonBackReference("participant-stageParticipant")
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @OneToMany(mappedBy = "stageParticipant", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("stageParticipant-grade")
    private List<Grade> gradeList;

    @ManyToOne
    @JsonBackReference("stage-stageParticipant")
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Column
    private boolean checkedIn;


}
