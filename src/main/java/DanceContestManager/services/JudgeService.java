package DanceContestManager.services;

import DanceContestManager.dtos.JudgeAlocRequestDTO;
import DanceContestManager.dtos.JudgeRequestDTO;
import DanceContestManager.entities.Contest;
import DanceContestManager.entities.Division;
import DanceContestManager.entities.Judge;
import DanceContestManager.exceptions.ResourceNotFoundException;
import DanceContestManager.repositories.ContestRepository;
import DanceContestManager.repositories.DivisionRepository;
import DanceContestManager.repositories.JudgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JudgeService {

    private JudgeRepository judgeRepository;
    private DivisionRepository divisionRepository;
    private ContestRepository contestRepository;

    public Judge createNewJudge(JudgeRequestDTO judgeRequestDTO) {
        Judge judge = new Judge();
        judge.setName(judgeRequestDTO.getName());
        judge.setCountry(judgeRequestDTO.getCountry());
        return judge;
    }

    @Transactional
    public Judge addJudgeToDivision(Long judge_id, JudgeAlocRequestDTO judgeAlocRequestDTO) {

        Judge judge = judgeRepository.findById(judge_id).orElseThrow(() -> new ResourceNotFoundException("Judge negasit"));
        Contest contest = contestRepository.findContestByName(judgeAlocRequestDTO.getContestName());
        Division division = divisionRepository.findDivisionByDivisionTypeAndContest(judgeAlocRequestDTO.getDivisionType(), contest);
        judge.setDivision(division);
        return judgeRepository.save(judge);
    }

    public List<Judge> findAllJuges() {
        return judgeRepository.findAll();
    }

    public List<Judge> findAllJugesByDivision(Long division_id) {
        Division division = divisionRepository.findById(division_id).orElseThrow(() -> new ResourceNotFoundException("Division negasită"));
        return judgeRepository.findAllByDivision(division);
    }

    public List<Judge> findAllJugesByContest(Long contest_id) {
        Contest contest = contestRepository.findById(contest_id).orElseThrow(() -> new ResourceNotFoundException("Contest negasit"));
        return contest.getDivisionList().stream()
                .flatMap(division -> division.getJudgeList().stream())
                .collect(Collectors.toList());
    }

    public boolean deleteJudge(Long judge_id) {
        Optional<Judge> judgeOptional = judgeRepository.findById(judge_id);
        if (judgeOptional.isPresent()) {
            judgeRepository.delete(judgeOptional.get());
            return true;
        } else {
            return false;
        }
    }

}
