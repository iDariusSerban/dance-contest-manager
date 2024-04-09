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
    @JsonManagedReference("stage-stageParticipant")
    private List<StageParticipant> stageParticipantList;



    public Stage(StageType stageType, Division division) {
        this.stageType = stageType;
        this.division = division;
    }


}
