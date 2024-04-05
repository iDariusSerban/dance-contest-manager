package DanceContestManager.controllers;

import DanceContestManager.dtos.GradeRequestDTO;
import DanceContestManager.entities.Grade;
import DanceContestManager.services.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
public class GradeController {
    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @PostMapping("/add")
    public ResponseEntity<Grade> addGrade(@RequestBody GradeRequestDTO gradeRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.addgrade(gradeRequestDTO));
    }

    //TODO delete grade
    //TODO update grade
    //TODO getContestGradesByParticipant   getStageGradeByParticipant   getAllGrades   getAllGradesByContest  getAllGradesByStage
}
