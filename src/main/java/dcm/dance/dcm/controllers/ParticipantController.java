package dcm.dance.dcm.controllers;

import dcm.dance.dcm.dtos.ParticipantRequestDTO;
import dcm.dance.dcm.entities.Participant;
import dcm.dance.dcm.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    private ParticipantService participantService;
@Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping("/add")
    public ResponseEntity<Participant> addParticipant(@RequestBody ParticipantRequestDTO participantRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.addParticipant(participantRequestDTO));
    }
}
