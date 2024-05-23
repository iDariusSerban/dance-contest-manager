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
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ParticipantService {

    private StageRepository stageRepository;
    private ParticipantRepository participantRepository;
    private StageParticipantRepository stageParticipantRepository;
    private ContestRepository contestRepository;
    private DivisionRepository divisionRepository;
    private QrCodeService qrCodeService;
    private JavaMailSender emailSender;

    @Transactional
    public void graduateStage(Participant participant, Stage stage) throws Exception {

        if (stage.getStageType().equals(StageType.Final)) {
            throw new Exception("Acesta este ultimul stage.");
        }

        Stage nextStage = stageRepository.findById(stage.getId() + 1).orElseThrow(() -> new ResourceNotFoundException("Stage not found"));
        Integer contestNumber = stageParticipantRepository.findByParticipantIdAndStageId(participant.getId(), stage.getId()).getContestNumber();

        StageParticipant stageParticipant = new StageParticipant();
        stageParticipant.setParticipant(participant);
        stageParticipant.setStage(nextStage);
        stageParticipant.setCheckedIn(true);
        stageParticipant.setContestNumber(contestNumber);
        stageParticipantRepository.save(stageParticipant);
    }

    public void determineAverageGrade(StageParticipant stageParticipant) {
        OptionalDouble average = stageParticipant.getGradeList().stream()
                .mapToDouble(Grade::getGradeValue)
                .average();
        stageParticipant.setAvgGrade(average.orElse(0.0));
    }

    public List<Participant> getEligibleForPromotionParticipants(List<Participant> participantList) {
        List<Participant> leaders = participantList.stream()
                .filter(participant -> participant.getRole().name().equals("leader"))
                .toList();

        List<Participant> followers = participantList.stream()
                .filter(participant -> participant.getRole().name().equals("follower"))
                .toList();

        List<Participant> qualified = new ArrayList<>();
        qualified.addAll(leaders.subList(0, leaders.size() / 2));
        qualified.addAll(followers.subList(0, leaders.size() / 2));
        return qualified;
    }


    @Transactional
    public Participant addParticipant(ParticipantRequestDTO participantRequestDTO) throws IOException, WriterException, MessagingException {

        Participant participant = mapFromDTO(participantRequestDTO);
        StageParticipant stageParticipant = createStageParticipant(participantRequestDTO, participant);
        participant.getStageParticipantList().add(stageParticipant);

        byte[] qrCode = qrCodeService.generateQrCode(stageParticipant.getContestNumber().toString());
        String ecnoded = Base64.getEncoder().encode(qrCode).toString();
        stageParticipant.setQrCode(qrCode);


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
        stageParticipantRepository.save(stageParticipant);
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

    public Participant findParticipant(Long participantId) {
        return participantRepository.findById(participantId).orElseThrow(() -> new ResourceNotFoundException("Participantul nu a fost gasit."));
    }
}
