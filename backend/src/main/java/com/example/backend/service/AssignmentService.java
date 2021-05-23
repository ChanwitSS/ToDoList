package com.example.backend.service;

import com.example.backend.model.Assignment;
import com.example.backend.repositories.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private  AssignmentRepository assignmentRepository;

    public List<Assignment> getAllAssignment(){
        System.out.println("Show all!!");
        System.out.println(assignmentRepository.findAll());
        return  assignmentRepository.findAll();
    }

    public Assignment getAssignment(Long id){
        return assignmentRepository.findByID(id);
    }

    public void addAssignment(Assignment assignment) {
        Optional <Assignment> asm = assignmentRepository.findByName(assignment.getAssignmentName());
        if (asm.isPresent()){
            throw new IllegalStateException("Name taken");
        }
        assignmentRepository.save(assignment);
        System.out.println("Add!!");
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }

    @Transactional
    public void updateAssignment(Assignment assignment){
        Assignment asm = assignmentRepository.findByID(assignment.getId());

        asm.setAssignmentName(assignment.getAssignmentName());
        asm.setStartDate(assignment.getStartDate());
        asm.setEndDate(assignment.getEndDate());
        asm.setDescription(assignment.getDescription());
        asm.setAssignedPerson(assignment.getAssignedPerson());
    }

}
