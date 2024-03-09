package DanceContestManager.dtos;

import DanceContestManager.entities.Judge;
import DanceContestManager.entities.Participant;
import DanceContestManager.entities.Stage;

public class GradeRequestDTO {
    private int gradeValue;
    private Long participantID;
     private String judgeName;
     private Long stageId;


    public GradeRequestDTO(int gradeValue, Long participantID, String judgeName, Long stageId) {
        this.gradeValue = gradeValue;
        this.participantID = participantID;
        this.judgeName = judgeName;
        this.stageId = stageId;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Long getParticipantID() {
        return participantID;
    }

    public void setParticipantID(Long participantID) {
        this.participantID = participantID;
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }
}
