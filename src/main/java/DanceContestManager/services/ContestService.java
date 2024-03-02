package DanceContestManager.services;

import DanceContestManager.dtos.ContestRequestDTO;
import DanceContestManager.entities.*;
import DanceContestManager.repositories.ContestRepository;
import DanceContestManager.repositories.DivisionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContestService {

    private ContestRepository contestRepository;
    private DivisionRepository divisionRepository;

    @Autowired
    public ContestService(ContestRepository contestRepository, DivisionRepository divisionRepository) {
        this.contestRepository = contestRepository;
        this.divisionRepository = divisionRepository;
    }

    @Transactional
    public Contest addContest(ContestRequestDTO contestRequestDTO) {

        Contest contest = new Contest();
        contest.setName(contestRequestDTO.getName());
        contest.setLocation(contestRequestDTO.getLocation());

        // setez cate o divizie de fiecare nivel pt concursul creat
        initializeContestWithDivisions(contest);

        return contestRepository.save(contest);
    }

    private static void initializeContestWithDivisions(Contest contest) {
        List<Division> divisionList = new ArrayList<>();
        for (DivisionType divisionType : DivisionType.values()) {
            Division division =new Division(divisionType, contest);
            initializeDivisionWithStages(division);
            divisionList.add(division);
        }
        contest.setDivisionList(divisionList);
    }

    private static void initializeDivisionWithStages(Division division) {
        List <Stage> stageList = new ArrayList<>();
        for (StageType stageType : StageType.values()) {
            stageList.add(new Stage(stageType, division));
        }
        division.setStageList(stageList);
    }
}
