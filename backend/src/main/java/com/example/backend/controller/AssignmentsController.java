package com.example.backend.controller;

import com.example.backend.model.Assignment;
import com.example.backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AssignmentsController {
    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping("/assignments")
    public List<Assignment> getAllAssignments(){
        return assignmentService.getAllAssignment();
    }

    @GetMapping("/assignments/{id}")
    public ResponseEntity<Assignment> get(@RequestBody Assignment assignment) {

        for(Assignment asm: assignmentService.getAllAssignment()){
            if (asm.getAssignmentName().equals(assignment.getAssignmentName())){
                return new ResponseEntity<Assignment>(asm, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Assignment>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/assignments/add")
    public void addAssignment(@RequestBody Assignment assignment) {
        assignmentService.addAssignment(assignment);
    }

    @DeleteMapping("/assignments/{id}")
    public void deleteAssignment(@PathVariable("id") Long id) {
        assignmentService.deleteAssignment(id);
    }

    @Transactional("/assignments/{id}")
    public void editAssignment(@RequestBody Assignment assignment){
        assignmentService.editAssignment(assignment);
    }

}
