package DanceContestManager.controllers;

import DanceContestManager.dtos.ParticipantRequestDTO;
import DanceContestManager.entities.Participant;
import DanceContestManager.services.ParticipantService;
import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
    public ResponseEntity<Participant> addParticipant(@RequestBody ParticipantRequestDTO participantRequestDTO) throws IOException, WriterException, MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.addParticipant(participantRequestDTO));
    }

    @PutMapping("/checkIn/{id}")
    public ResponseEntity<Participant> checkInParticipant(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.checkInParticipant(id));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Participant> findParticipant(@PathVariable Long id) {
        return ResponseEntity.ok(participantService.findParticipant(id));
    }

    @DeleteMapping("/delete/{participant_id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long participant_id) {
        boolean deleted = participantService.deleteParticipant(participant_id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
