package DanceContestManager.services;

import DanceContestManager.entities.Participant;
import DanceContestManager.entities.Stage;
import DanceContestManager.entities.StageParticipant;
import DanceContestManager.exceptions.ResourceNotFoundException;
import DanceContestManager.repositories.StageParticipantRepository;
import DanceContestManager.repositories.StageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StageService {

    private StageParticipantRepository stageParticipantRepository;
    private ParticipantService participantService;
    private StageRepository stageRepository;

    @Transactional
    public void finalizeStage(Long stageId) throws Exception {
        Stage stage = stageRepository.findById(stageId).orElseThrow(() -> new ResourceNotFoundException("Stage-ul nu a fost gasit"));


        List<StageParticipant> stageParticipantList = stageParticipantRepository.findAllByStageId(stageId);
        stageParticipantList.forEach(stageParticipant -> participantService.determineAverageGrade(stageParticipant));

        List<StageParticipant> sortedStageParticipantList = stageParticipantList.stream()
                .sorted((sp1, sp2) -> Double.compare(sp2.getAvgGrade(), sp1.getAvgGrade()))
                .toList();

        List<Participant> participantList = sortedStageParticipantList.stream()
                .map(StageParticipant::getParticipant)
                .collect(Collectors.toList());

        List<Participant> qualified = participantService.getEligibleForPromotionParticipants(participantList);
        qualified.forEach(qualifiedParticipant -> {
            try {
                participantService.graduateStage(qualifiedParticipant,stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        stage.setCompleted(true);
        stageRepository.save(stage);

        //TODO generate report
    }
}
