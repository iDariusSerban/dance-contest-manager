package DanceContestManager.services;

import DanceContestManager.entities.Participant;
import DanceContestManager.entities.StageParticipant;
import DanceContestManager.repositories.StageParticipantRepository;
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

    public void finalizeStage(Long stageId) {
        // calculez media la fiecare

        //calculez media notelor participantului
        //separ participantii pe categorii, lider si follower
        //din totalul participantilor prima jumatate merge mai departe in urmatorul stage


        List<StageParticipant> stageParticipantList = stageParticipantRepository.findAllByOrderByAvgGradeDesc();

        List<Participant> participantList = stageParticipantList.stream()
                .map(StageParticipant::getParticipant)
                .collect(Collectors.toList());

        List<Participant> qualified = participantService.getEligibleForPromotionParticipants(participantList);
        qualified.forEach(qualifiedParticipant -> participantService.graduateStage(qualifiedParticipant));

        //TODO
        //pe stage-ul cu id-ul care vine ca parametru, sa il updatezi cu finalized true







    }
}
