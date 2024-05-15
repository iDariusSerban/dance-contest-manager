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
    public ResponseEntity<Void> finalizeStage(@PathVariable Long stageId) {

        return ResponseEntity.ok(stageService.finalizeStage(stageId));
    }


}
