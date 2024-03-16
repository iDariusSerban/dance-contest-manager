package DanceContestManager.controllers;

import DanceContestManager.dtos.ParticipantRequestDTO;
import DanceContestManager.entities.Participant;
import DanceContestManager.services.ParticipantService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

    private ParticipantService participantService;
@Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @PostMapping("/add")
    public ResponseEntity<Participant> addParticipant(@RequestBody ParticipantRequestDTO participantRequestDTO) throws IOException, WriterException {
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.addParticipant(participantRequestDTO));
    }
}
