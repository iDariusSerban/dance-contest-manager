package dcm.dance.dcm.services;

import dcm.dance.dcm.dtos.ParticipantRequestDTO;
import dcm.dance.dcm.entities.Participant;
import dcm.dance.dcm.entities.RoleType;
import dcm.dance.dcm.repositories.GradeRepository;
import dcm.dance.dcm.repositories.ParticipantRepository;
import dcm.dance.dcm.repositories.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    private ParticipantRepository participantRepository;
    private StageRepository stageRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, StageRepository stageRepository, GradeRepository gradeRepository) {
        this.participantRepository = participantRepository;
        this.stageRepository = stageRepository;
        this.gradeRepository = gradeRepository;
    }

    public Participant addParticipant(ParticipantRequestDTO participantRequestDTO) {
        Participant participant = new Participant();
        participant.setName(participantRequestDTO.getName());
        participant.setCountry(participantRequestDTO.getCountry());
        participant.setEmailAddress(participantRequestDTO.getEmailAddress());
        participant.setRole(participantRequestDTO.getRole());
        participant.setCheckedIn(false);

        //cum fac rolul sa fie doar optiunile leader si follower



        return participantRepository.save(participant);
    }
}
