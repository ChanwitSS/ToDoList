package com.example.backend.controller;

import com.example.backend.model.Assignment;
import com.example.backend.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentsController {
    private AssignmentService assignmentService;

    public AssignmentsController(AssignmentService assignmentService){
        this.assignmentService = assignmentService;
    }

    @RequestMapping("/assignments")
    public List<Assignment> getAllAssignments(){
        return assignmentService.getAllAssignment();
    }

    @GetMapping("/assignments/{assignmentName}")
    public ResponseEntity<Assignment> get(@RequestBody Assignment assignment) {

        for(Assignment asm: assignmentService.getAllAssignment()){
            if (asm.getAssignmentName().equals(assignment.getAssignmentName())){
                return new ResponseEntity<Assignment>(asm, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Assignment>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/assignments/{assignmentName}")
    public void addAssignment(@RequestBody Assignment assignment) {
        assignmentService.addAssignment(assignment);
    }

    @DeleteMapping("/assignments/{assignmentName}")
    public void deleteAssignment(@RequestBody Assignment assignment) {
        assignmentService.deleteAssignment(assignment);
    }

}
