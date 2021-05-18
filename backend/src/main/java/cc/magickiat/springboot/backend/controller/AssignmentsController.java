package cc.magickiat.springboot.backend.controller;
import cc.magickiat.springboot.backend.model.Assignment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class AssignmentsController {

    List<Assignment> assignments = new ArrayList<>(Arrays.asList(
            new Assignment("Java", "01/01/2021", "02/01/2021","Coding java program", "Mark"),
            new Assignment("Python", "04/02/2021", "06/02/2021","Coding python program", "Elon")
    ));

    @RequestMapping("/assignments")
    public List<Assignment> getAllAssignments(){
        return assignments;
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> get(@PathVariable String id) {
        Assignment assignment = null;
        for(Assignment a:assignments){
            if (a.getAssignmentName().equals(id)){
                assignment = a;
                return new ResponseEntity<Assignment>(assignment, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Assignment>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/assignments/{id}")
    public void addAssignment(@RequestBody Assignment assignment, @PathVariable String id) {
        assignments.add(assignment);
    }

    @DeleteMapping("/asignments/{id}")
    public void deleteAssignment(@PathVariable String assignmentName) {
        for(int i = 0; i < assignments.size(); i++) {
            if(assignments.get(i).getAssignmentName().equals(assignmentName)) {
                assignments.remove(i);
                break;
            }
        }
    }
}
