package DanceContestManager.services;

import DanceContestManager.dtos.JudgeRequestDTO;
import DanceContestManager.entities.Contest;
import DanceContestManager.entities.Division;
import DanceContestManager.entities.Judge;
import DanceContestManager.repositories.ContestRepository;
import DanceContestManager.repositories.DivisionRepository;
import DanceContestManager.repositories.JudgeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JudgeService {

    private JudgeRepository judgeRepository;
    private DivisionRepository divisionRepository;
    private ContestRepository contestRepository;

    public JudgeService(JudgeRepository judgeRepository, DivisionRepository divisionRepository, ContestRepository contestRepository) {
        this.judgeRepository = judgeRepository;
        this.divisionRepository = divisionRepository;
        this.contestRepository = contestRepository;
    }

    //TODO refactor to get also the judge id and to assign existing judge to division of contest
    //TODO another method that just creates a judge
    @Transactional
    public Judge addJudge(JudgeRequestDTO judgeRequestDTO) {
        Judge judge = new Judge();
        judge.setName(judgeRequestDTO.getName());

        Contest contest = contestRepository.findContestByName(judgeRequestDTO.getContestName());
        Division division = divisionRepository.findDivisionByDivisionTypeAndContest(judgeRequestDTO.getDivisionType(), contest);
        judge.setDivision(division);
        return judgeRepository.save(judge);
    }
}
