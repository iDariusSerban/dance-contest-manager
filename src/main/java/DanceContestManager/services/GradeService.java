package DanceContestManager.services;

import DanceContestManager.dtos.GradeRequestDTO;
import DanceContestManager.entities.Grade;
import DanceContestManager.repositories.GradeRepository;
import DanceContestManager.repositories.JudgeRepository;
import DanceContestManager.repositories.ParticipantRepository;
import DanceContestManager.repositories.StageRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private GradeRepository gradeRepository;
    private JudgeRepository judgeRepository;
    private StageRepository stageRepository;
    private ParticipantRepository participantRepository;

    public GradeService(GradeRepository gradeRepository, JudgeRepository judgeRepository, StageRepository stageRepository, ParticipantRepository participantRepository) {
        this.gradeRepository = gradeRepository;
        this.judgeRepository = judgeRepository;
        this.stageRepository = stageRepository;
        this.participantRepository = participantRepository;
    }

    public Grade addgrade (GradeRequestDTO gradeRequestDTO){
        Grade grade =new Grade();
        grade.setGradeValue(gradeRequestDTO.getGradeValue());
        grade.setJudge(judgeRepository.findJudgeByNameIs(gradeRequestDTO.getJudgeName()));
        grade.setParticipant(participantRepository.findParticipantById(gradeRequestDTO.getParticipantID()));

        return gradeRepository.save(grade);
    }
}
