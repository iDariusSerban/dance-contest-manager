package DanceContestManager.services;

import DanceContestManager.entities.*;
import DanceContestManager.repositories.*;
import DanceContestManager.dtos.ParticipantRequestDTO;
import com.google.zxing.WriterException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private ParticipantRepository participantRepository;
    private StageParticipantRepository stageParticipantRepository;
    private ContestRepository contestRepository;
    private DivisionRepository divisionRepository;
    private GradeRepository gradeRepository;
    private QrCodeService qrCodeService;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, StageParticipantRepository stageParticipantRepository, ContestRepository contestRepository, DivisionRepository divisionRepository, GradeRepository gradeRepository, QrCodeService qrCodeService) {
        this.participantRepository = participantRepository;
        this.stageParticipantRepository = stageParticipantRepository;
        this.contestRepository = contestRepository;
        this.divisionRepository = divisionRepository;
        this.gradeRepository = gradeRepository;
        this.qrCodeService = qrCodeService;
    }


    @Transactional
    public Participant addParticipant(ParticipantRequestDTO participantRequestDTO) throws IOException, WriterException {
        Participant participant = mapFromDTO(participantRequestDTO);
        participant.getStageParticipantList().add(createStageParticipant(participantRequestDTO, participant));
        //TODO de facut o metoda care verifica daca participantul se adauga in pimul stage al diviziei (doar asa ii generam qr code)
       // if (isAddedToFirstStage)
// generez QR code


        byte[] qrCode = qrCodeService.generateQrCode(generateContestNumber().toString());
        //trimiti mail la participant.getMail() cu atasament qr code in format png
        //salvezi in stageparticipant qrcode-ul generat



        return participantRepository.save(participant);
    }

    public Integer generateContestNumber() {

        Optional<StageParticipant> stageParticipant = stageParticipantRepository.findTopByOrderByContestNumberDesc();
        return stageParticipant.map(participant -> participant.getContestNumber() + 1).orElse(1);

    }

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

    public Participant mapFromDTO(ParticipantRequestDTO participantRequestDTO) {
        Participant participant = new Participant();
        participant.setName(participantRequestDTO.getName());
        participant.setCountry(participantRequestDTO.getCountry());
        participant.setEmailAddress(participantRequestDTO.getEmailAddress());
        participant.setRole(participantRequestDTO.getRole());
        return participant;
    }
}
