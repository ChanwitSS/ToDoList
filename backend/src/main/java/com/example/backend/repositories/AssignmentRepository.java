package com.example.backend.repositories;

import com.example.backend.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    @Query("SELECT asm FROM assignment WHERE asm.assignmentName = ?1")
    Optional<Assignment> findAssignmentByName(String name);
}
