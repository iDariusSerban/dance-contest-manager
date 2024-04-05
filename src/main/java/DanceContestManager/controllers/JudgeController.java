package DanceContestManager.controllers;

import DanceContestManager.dtos.JudgeRequestDTO;
import DanceContestManager.entities.Judge;
import DanceContestManager.services.JudgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/judge")
public class JudgeController {
    private JudgeService judgeService;

    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Judge> addJudge(@RequestBody JudgeRequestDTO judgeRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(judgeService.addJudge(judgeRequestDTO));
    }


    //TODO findAllJudges, findAllByContest findAllByDivision
    //TODO deleteJudge
    //TODO createNewJudge

}


