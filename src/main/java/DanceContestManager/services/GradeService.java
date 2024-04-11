package DanceContestManager.services;

import DanceContestManager.dtos.GradeRequestDTO;
import DanceContestManager.dtos.GradeResponseDTO;
import DanceContestManager.entities.Grade;
import DanceContestManager.entities.StageParticipant;
import DanceContestManager.exceptions.ResourceNotFoundException;
import DanceContestManager.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GradeService {

    private GradeRepository gradeRepository;
    private JudgeRepository judgeRepository;
    private StageParticipantRepository stageParticipantRepository;


    public Grade addgrade(GradeRequestDTO gradeRequestDTO) {
        Grade grade = new Grade();
        grade.setGradeValue(gradeRequestDTO.getGradeValue());
        grade.setJudge(judgeRepository.findJudgeByNameIs(gradeRequestDTO.getJudgeName()));
        grade.setStageParticipant(stageParticipantRepository.findById(gradeRequestDTO.getStageParticipantId()).orElseThrow(() -> new ResourceNotFoundException("StageParticipant negasit")));
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(GradeRequestDTO gradeRequestDTO) {
        Grade grade = gradeRepository.findById(gradeRequestDTO.getGrade_id()).orElseThrow(() -> new ResourceNotFoundException("Grade negasit"));
        grade.setGradeValue(gradeRequestDTO.getGradeValue());
        return gradeRepository.save(grade);
    }

    public List<GradeResponseDTO> findAll() {
        List<Grade> gradeList = gradeRepository.findAll();
        return gradeList.stream()
                .map(this::mapFromGradeToDTO)
                .collect(Collectors.toList());
    }


    public GradeResponseDTO mapFromGradeToDTO(Grade grade) {
        GradeResponseDTO gradeResponseDTO = new GradeResponseDTO();
        gradeResponseDTO.setGradeId(grade.getId());
        gradeResponseDTO.setGradeValue(grade.getGradeValue());
        gradeResponseDTO.setJudgeId(grade.getJudge().getId());
        gradeResponseDTO.setParticipantId(grade.getStageParticipant().getParticipant().getId());
        gradeResponseDTO.setStageId(grade.getStageParticipant().getStage().getId());
        gradeResponseDTO.setDivisionId(grade.getStageParticipant().getStage().getDivision().getId());
        return gradeResponseDTO;
    }

    public Boolean deleteGrade(Long grade_id) {
        Optional<Grade> gradeOptional = gradeRepository.findById(grade_id);
        if (gradeOptional.isPresent()) {
            gradeRepository.delete(gradeOptional.get());
            return true;
        } else {
            return false;
        }

    }

    public Double calculateAvgGradePerParticipant(StageParticipant stageParticipant) {
        return stageParticipant.getGradeList().stream()
                .map(Grade::getGradeValue)
                .mapToDouble(Double::doubleValue)
                .average().orElseThrow(() -> new ResourceNotFoundException("Nu exista note"));

    }
}
