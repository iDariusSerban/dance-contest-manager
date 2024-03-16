package DanceContestManager.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

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


}