package com.example.backend.model;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String assignmentName;
    private String assignedPerson;
    private String startDate;
    private String endDate;
    private String description;

    public Assignment(){
    }

    public Assignment(Long id, String assignmentName, String startDate, String endDate, String description, String assignedPerson) {
        this.id = id;
        this.assignmentName = assignmentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.assignedPerson = assignedPerson;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public String getAssignedPerson() {
        return assignedPerson;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public void setAssignedPerson(String assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
