package DanceContestManager.controllers;


import DanceContestManager.services.ContestService;
import DanceContestManager.dtos.ContestRequestDTO;
import DanceContestManager.entities.Contest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/contest")
public class ContestController {


    private ContestService contestService;


    @PostMapping("/add")
    public ResponseEntity<Contest> addContest(@RequestBody ContestRequestDTO contestRequestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(contestService.addContest(contestRequestDTO));
    }

    @PutMapping("/update/{contest_id}")
    public ResponseEntity<Contest> updateContest(@PathVariable  Long contest_id,@RequestBody ContestRequestDTO contestRequestDTO) {

        return ResponseEntity.ok(contestService.updateContest(contest_id, contestRequestDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Contest>> findAll(){
        return ResponseEntity.ok(contestService.findAll());
    }

    @GetMapping("/findById/{contest_id}")
    public ResponseEntity<Contest> findById(@PathVariable Long contest_id){
        return ResponseEntity.ok(contestService.findById(contest_id));
    }

    @DeleteMapping("/delete/{contest_id}")
    public ResponseEntity<List<Contest>> deleteContestById(@PathVariable Long contest_id) {
        return ResponseEntity.ok(contestService.deleteContestById(contest_id));
    }



}
