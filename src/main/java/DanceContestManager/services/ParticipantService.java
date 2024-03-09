package DanceContestManager.services;

import DanceContestManager.entities.*;
import DanceContestManager.repositories.*;
import DanceContestManager.dtos.ParticipantRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantService {

    private ParticipantRepository participantRepository;
    private StageRepository stageRepository;
    private ContestRepository contestRepository;
    private DivisionRepository divisionRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, StageRepository stageRepository, ContestRepository contestRepository, DivisionRepository divisionRepository, GradeRepository gradeRepository) {
        this.participantRepository = participantRepository;
        this.stageRepository = stageRepository;
        this.contestRepository = contestRepository;
        this.divisionRepository = divisionRepository;
        this.gradeRepository = gradeRepository;
    }

    @Transactional
    public Participant addParticipant(ParticipantRequestDTO participantRequestDTO) {
        Participant participant = new Participant();
        participant.setName(participantRequestDTO.getName());
        participant.setCountry(participantRequestDTO.getCountry());
        participant.setEmailAddress(participantRequestDTO.getEmailAddress());
        participant.setRole(participantRequestDTO.getRole());
        participant.getStageParticipantList().add(createStageParticipant(participantRequestDTO, participant));
        return participantRepository.save(participant);
    }

    @Transactional
    public StageParticipant createStageParticipant(ParticipantRequestDTO participantRequestDTO, Participant participant) {
        StageParticipant stageParticipant = new StageParticipant();
        stageParticipant.setStage(getStage(participantRequestDTO));
        stageParticipant.setParticipant(participant);
        stageParticipant.setCheckedIn(false);

        return stageParticipant;
    }

    @Transactional
    public Stage getStage(ParticipantRequestDTO participantRequestDTO) {

        Contest contest = contestRepository.findContestByName(participantRequestDTO.getContestName());
        Division division = divisionRepository.findDivisionByDivisionTypeAndContest(participantRequestDTO.getDivisionType(), contest);

        return division.getStageList().get(0);
    }


}
