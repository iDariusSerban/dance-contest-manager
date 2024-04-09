package DanceContestManager.controllers;

import DanceContestManager.dtos.JudgeAlocRequestDTO;
import DanceContestManager.dtos.JudgeRequestDTO;
import DanceContestManager.entities.Judge;
import DanceContestManager.services.JudgeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/judge")
public class JudgeController {
    private JudgeService judgeService;


    @PostMapping("/create")
    public ResponseEntity<Judge> createNewJudge(@RequestBody JudgeRequestDTO judgeRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(judgeService.createNewJudge(judgeRequestDTO));
    }

    @PutMapping("/add/{judge_id}")
    public ResponseEntity<Judge> addJudgeToDivision(@PathVariable Long judge_id, @RequestBody JudgeAlocRequestDTO judgeAlocRequestDTO) {
        return ResponseEntity.ok(judgeService.addJudgeToDivision(judge_id, judgeAlocRequestDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Judge>> findAllJudges() {
        return ResponseEntity.ok(judgeService.findAllJuges());
    }

    @GetMapping("/findAllByDivision/{division_id}")
    public ResponseEntity<List<Judge>> findAllJudgesByDivision(@PathVariable Long division_id) {
        return ResponseEntity.ok(judgeService.findAllJugesByDivision(division_id));
    }

    @GetMapping("/findAllByContest/{contest_id}")
    public ResponseEntity<List<Judge>> findAllJudgesByContest(@PathVariable Long contest_id) {
        return ResponseEntity.ok(judgeService.findAllJugesByContest(contest_id));
    }


    @DeleteMapping("/delete/{judge_id}")
    public ResponseEntity<Void> deleteJudge(@PathVariable Long judge_id) {
        boolean deleted = judgeService.deleteJudge(judge_id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}


