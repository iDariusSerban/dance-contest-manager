package DanceContestManager.services;

import DanceContestManager.entities.Participant;
import DanceContestManager.entities.StageParticipant;
import DanceContestManager.repositories.StageParticipantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StageService {

    private StageParticipantRepository stageParticipantRepository;

    public List<Participant> finalizeStage(Long stageId) {
        // calculez media la fiecare


        List<StageParticipant> stageParticipantList = stageParticipantRepository.findAllByOrderByAvgGradeDesc();
        //TODO iau doar jumatate din participanti
        // ii separ in leaderi si followeri
        return stageParticipantList.stream()
                .map(StageParticipant::getParticipant)
                .collect(Collectors.toList());

    }
}
