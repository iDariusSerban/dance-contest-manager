package DanceContestManager.controllers;

import DanceContestManager.dtos.ParticipantRequestDTO;
import DanceContestManager.entities.Participant;
import DanceContestManager.services.ParticipantService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/participant")
public class ParticipantController {

    private ParticipantService participantService;


    @PostMapping("/add")
    public ResponseEntity<Participant> addParticipant(@RequestBody ParticipantRequestDTO participantRequestDTO) throws IOException, WriterException {
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.addParticipant(participantRequestDTO));
    }

    //TODO checkinParticipant
//    @PutMapping ("/checkInParticipant")
//    public ResponseEntity<Participant> checkInParticipant (){
//        return ResponseEntity.ok(participantService.)
//    }

    @DeleteMapping("/delete/{participant_id}")
    public ResponseEntity<Void> deleteParticiapnt(@PathVariable Long participant_id) {
        boolean deleted = participantService.deleteParticipant(participant_id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
