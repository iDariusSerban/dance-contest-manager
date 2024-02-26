package dcm.dance.dcm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private DivisionType divisionType;
    @ManyToOne
    @JsonBackReference("contest-division")
    @JoinColumn(name = "contest_id")
    private Contest contest;
    @OneToMany(mappedBy = "Division",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("division-stage")
    private List<Stage> stageList;
    @OneToMany(mappedBy = "Division",  cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JsonManagedReference("division-judge")
    private List<Judge> judgeList;

    public Division() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DivisionType getDivisionType() {
        return divisionType;
    }

    public void setDivisionType(DivisionType divisionType) {
        this.divisionType = divisionType;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }

    public List<Judge> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(List<Judge> judgeList) {
        this.judgeList = judgeList;
    }
}
