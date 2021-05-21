package com.example.backend.model;
import javax.persistence.*;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue
    private String assignmentName;
    private String assignedPerson;
    private String startDate;
    private String endDate;
    private String description;

    public Assignment(String assignmentName, String startDate, String endDate, String description, String assignedPerson) {
        this.description = description;
        this.assignmentName = assignmentName;
        this.assignedPerson = assignedPerson;
        this.startDate = startDate;
        this.endDate = endDate;
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

}
