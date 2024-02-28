package dcm.dance.dcm.controllers;


import dcm.dance.dcm.dtos.ContestRequestDTO;
import dcm.dance.dcm.entities.Contest;
import dcm.dance.dcm.services.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contest")
public class ContestController {


    private ContestService contestService;

    @Autowired
    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @PostMapping("/add")
    public ResponseEntity<Contest> addContest(@RequestBody ContestRequestDTO contestRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(contestService.addContest(contestRequestDTO));
        } catch (Exception e) {
            // Adaugă un logging pentru a afișa detalii despre excepție
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       // return ResponseEntity.status(HttpStatus.CREATED).body(contestService.addContest(contestRequestDTO));
    }
}
