package DanceContestManager.services;

import DanceContestManager.dtos.GradeRequestDTO;
import DanceContestManager.entities.Grade;
import DanceContestManager.entities.StageParticipant;
import DanceContestManager.repositories.GradeRepository;
import DanceContestManager.repositories.JudgeRepository;
import DanceContestManager.repositories.ParticipantRepository;
import DanceContestManager.repositories.StageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeService {

    private GradeRepository gradeRepository;
    private JudgeRepository judgeRepository;
    private StageRepository stageRepository;
    private ParticipantRepository participantRepository;


    //TODO update logic when grade is relate to stageparticipant

    public Grade addgrade (GradeRequestDTO gradeRequestDTO){
        Grade grade =new Grade();
        grade.setGradeValue(gradeRequestDTO.getGradeValue());
        grade.setJudge(judgeRepository.findJudgeByNameIs(gradeRequestDTO.getJudgeName()));
       // StageParticipant stageParticipant =
                // caut participantul
        //caut stage

        //grade.setParticipant(participantRepository.findParticipantById(gradeRequestDTO.getParticipantID()));

        return gradeRepository.save(grade);
    }
}
