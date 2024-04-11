package DanceContestManager.controllers;

import DanceContestManager.dtos.GradeRequestDTO;
import DanceContestManager.dtos.GradeResponseDTO;
import DanceContestManager.entities.Grade;
import DanceContestManager.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grade")
public class GradeController {
    private GradeService gradeService;

    @PostMapping("/add")
    public ResponseEntity<Grade> addGrade(@RequestBody GradeRequestDTO gradeRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.addgrade(gradeRequestDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<Grade> updateGrade(@RequestBody GradeRequestDTO gradeRequestDTO) {
        return ResponseEntity.ok(gradeService.updateGrade(gradeRequestDTO));
    }

    @DeleteMapping("/delete/{grade_id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long grade_id) {
        boolean deleted = gradeService.deleteGrade(grade_id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<GradeResponseDTO>> findAllGrades() {
        return ResponseEntity.ok(gradeService.findAll());
    }



    //TODO getContestGradesByParticipant   getStageGradeByParticipant      getAllGradesByContest  getAllGradesByStage
}
