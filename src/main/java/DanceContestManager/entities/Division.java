package DanceContestManager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@NoArgsConstructor

public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private DivisionType divisionType;
    @ManyToOne
    @JsonBackReference("contest-division")
    @JoinColumn(name = "contest_id")
    private Contest contest;
    @OneToMany(mappedBy = "division", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("division-stage")
    private List<Stage> stageList;
    @OneToMany(mappedBy = "division", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("division-judge")
    private List<Judge> judgeList;



    public Division(DivisionType divisionType, Contest contest) {

        this.divisionType = divisionType;
        this.contest = contest;
        this.judgeList = new ArrayList<>();


    }


}
