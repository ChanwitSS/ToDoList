package com.example.backend.service;

import com.example.backend.model.Assignment;
import com.example.backend.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    /*List<Assignment> assignments = new ArrayList<>(Arrays.asList(
            new Assignment("Java", "01/01/2021", "02/01/2021","Coding java program", "Mark"),
            new Assignment("Python", "04/02/2021", "06/02/2021","Coding python program", "Elon")
    ));*/

    private AssignmentRepository assignmentRepository;
    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository){
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> getAllAssignment(){
        System.out.println("Show all!!");
        return assignmentRepository.findAll();
    }

    public void addAssignment(Assignment assignment) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findAssignmentByName(assignment.getAssignmentName());
        if (assignmentOptional.isPresent()){
            throw new IllegalStateException("Name taken");
        }
        assignmentRepository.save(assignment);
        System.out.println("Add!!");
    }

    public void deleteAssignment(Assignment assignment) {
        assignmentRepository.delete(assignment);
    }

    /*public void editAssignment(Assignment assignment){
        for(Assignment asm: assignments){
            if (asm.getAssignmentName().equals(assignment.getAssignmentName())){
                asm.setAssignmentName(assignment.getAssignmentName());
                asm.setStartDate(assignment.getStartDate());
                asm.setEndDate(assignment.getEndDate());
                asm.setDescription(assignment.getDescription());
                asm.setAssignedPerson(assignment.getAssignedPerson());
            }
        }
    }*/

}
