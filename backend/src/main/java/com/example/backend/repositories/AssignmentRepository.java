package com.example.backend.repositories;

import com.example.backend.model.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    @Query("SELECT asm FROM Assignment asm WHERE asm.assignmentName = ?1")
    Optional <Assignment> findByName(String name);

    @Query("SELECT asm FROM Assignment asm WHERE asm.id = ?1")
    Assignment findByID(Long id);

}
