package dcm.dance.dcm.services;

import dcm.dance.dcm.dtos.ContestRequestDTO;
import dcm.dance.dcm.entities.*;
import dcm.dance.dcm.repositories.ContestRepository;
import dcm.dance.dcm.repositories.DivisionRepository;
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

    public Contest addContest(ContestRequestDTO contestRequestDTO) {

        Contest contest = new Contest();
        contest.setName(contestRequestDTO.getName());
        contest.setLocation(contestRequestDTO.getLocation());

        // setez cate o divizie de fiecare nivel pt concursul creat
        List<Division> divisionList = new ArrayList<>();
        for (DivisionType divisionType : DivisionType.values()) {
            divisionList.add(new Division(divisionType));
        }
        contest.setDivisionList(divisionList);

        return contestRepository.save(contest);
    }
}
