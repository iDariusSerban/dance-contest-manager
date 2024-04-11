package DanceContestManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double gradeValue;
    @ManyToOne
    @JsonBackReference("judge-grade")
    @JoinColumn(name = "judge_id")
    private Judge judge;


    @ManyToOne
    @JsonBackReference("stageParticipant-grade")
    @JoinColumn(name = "stageParticipant_id")
    private StageParticipant stageParticipant;


}
