package DanceContestManager.controllers;

import DanceContestManager.entities.Judge;
import DanceContestManager.entities.Participant;
import DanceContestManager.services.StageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/stage")
public class StageController {

    private StageService stageService;

    @GetMapping("/finalizeStage/{stageId}")
    public ResponseEntity<List<Participant>> finalizeStage(@PathVariable Long stageId) {
        return ResponseEntity.ok(stageService.finalizeStage(stageId));
    }
    //TODO finalizeStage (de adaugat in db o coloana la stage care sa ne zica daca stage-ul s-a desfasurat sau nu
    //se calculeaza care participanti -au calificat la stage-ul urmator si se adauga toti in stage-ul urmator (folosind addParticipant)
    //se poate exprota in format PDF rezultatul cu claificatii din stage

}
