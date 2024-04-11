package DanceContestManager.services;

import DanceContestManager.entities.*;
import DanceContestManager.exceptions.ResourceNotFoundException;
import DanceContestManager.repositories.*;
import DanceContestManager.dtos.ParticipantRequestDTO;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParticipantService {

    private ParticipantRepository participantRepository;
    private StageParticipantRepository stageParticipantRepository;
    private ContestRepository contestRepository;
    private DivisionRepository divisionRepository;
    private GradeRepository gradeRepository;
    private QrCodeService qrCodeService;

    private JavaMailSender emailSender;


    public void graduateStage(Participant participant) { // poate e stageParticipant
        //calculez media notelor participantului
        //separ participantii pe categorii, lider si follower
        //din totalul participantilor prima jumatate merge mai departe in urmatorul stage

    }
    //TODO de facut o metoda care verifica daca participantul se adauga in pimul stage al diviziei (doar asa ii generam qr code)
    // if (isAddedToFirstStage)

    @Transactional
    public Participant addParticipant(ParticipantRequestDTO participantRequestDTO) throws IOException, WriterException, MessagingException {

        Participant participant = mapFromDTO(participantRequestDTO);
        StageParticipant stageParticipant = createStageParticipant(participantRequestDTO, participant);
        participant.getStageParticipantList().add(stageParticipant);

        byte[] qrCode = qrCodeService.generateQrCode(stageParticipant.getContestNumber().toString());
        String ecnoded = Base64.getEncoder().encode(qrCode).toString();
        stageParticipant.setQrCode(qrCode); //salvez in stageparticipant qrcode-ul generat


        //trimiti mail la participant.getMail() cu atasament qr code in format png
        sendEmailWithQRCode(participant.getEmailAddress(), qrCode, participantRequestDTO.getContestName());

        return participantRepository.save(participant);
    }

    private void sendEmailWithQRCode(String recipientEmail, byte[] qrCode, String contestName) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject("Confirmation of Registration:" + contestName);
        helper.setText("Your checkIn code is attached, please present it when you arrive at the venue.");

        helper.addAttachment("QRCode.png", new ByteArrayResource(qrCode));

        emailSender.send(message);
    }


    public StageParticipant createStageParticipant(ParticipantRequestDTO participantRequestDTO, Participant participant) {
        StageParticipant stageParticipant = new StageParticipant();
        stageParticipant.setStage(getStage(participantRequestDTO));
        stageParticipant.setParticipant(participant);
        stageParticipant.setContestNumber(generateContestNumber());
        stageParticipant.setCheckedIn(false);

        return stageParticipant;
    }

    public Integer generateContestNumber() {

        Optional<StageParticipant> stageParticipant = stageParticipantRepository.findTopByOrderByContestNumberDesc();
        return stageParticipant.map(participant -> participant.getContestNumber() + 1).orElse(1);
    }

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

    public Participant checkInParticipant(Long stageParticipantId) {
        StageParticipant stageParticipant = stageParticipantRepository.findById(stageParticipantId).orElseThrow(() -> new ResourceNotFoundException("StageParticipant negasit"));
        stageParticipant.setCheckedIn(true);

        return stageParticipant.getParticipant();
    }

    public boolean deleteParticipant(Long participant_id) {
        Optional<Participant> participantOptional = participantRepository.findById(participant_id);
        if (participantOptional.isPresent()) {
            participantRepository.delete(participantOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
