package DanceContestManager.services;

import DanceContestManager.dtos.ContestRequestDTO;
import DanceContestManager.entities.*;
import DanceContestManager.exceptions.ResourceNotFoundException;
import DanceContestManager.repositories.ContestRepository;
import DanceContestManager.repositories.DivisionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ContestService {

    private ContestRepository contestRepository;
    private DivisionRepository divisionRepository;


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
            Division division = new Division(divisionType, contest);
            initializeDivisionWithStages(division);
            divisionList.add(division);
        }
        contest.setDivisionList(divisionList);
    }

    private static void initializeDivisionWithStages(Division division) {
        List<Stage> stageList = new ArrayList<>();
        for (StageType stageType : StageType.values()) {
            stageList.add(new Stage(stageType, division));
        }
        division.setStageList(stageList);
    }

    public Contest updateContest(Long contest_id, ContestRequestDTO contestRequestDTO) {
        Contest contest = contestRepository.findById(contest_id).orElseThrow(() -> new ResourceNotFoundException("Concursul nu există"));
        contest.setName(contestRequestDTO.getName());
        contest.setLocation(contestRequestDTO.getLocation());
        return contestRepository.save(contest);
    }

   public Contest findById (Long contest_id){
        return contestRepository.findById(contest_id).orElseThrow(() -> new ResourceNotFoundException("Concursul nu există"));
   }

    public List<Contest> findAll() {
        return contestRepository.findAll();
    }
    public List<Contest> deleteContestById(Long contest_id) {
        Contest contest = contestRepository.findById(contest_id).orElseThrow(() -> new ResourceNotFoundException("Concursul nu există"));
        contestRepository.delete(contest);
        return findAll();
    }
}
