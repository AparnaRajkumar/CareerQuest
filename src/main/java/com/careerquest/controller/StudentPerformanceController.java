package com.careerquest.controller;

import com.careerquest.model.StudentPerformance;
import com.careerquest.repository.StudentPerformanceRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentPerformanceController {

    private final StudentPerformanceRepository repository;

    public StudentPerformanceController(
            StudentPerformanceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}/performance")
    public ResponseEntity<StudentPerformance> getPerformance(
            @PathVariable Long id) {

        return repository.findByStudent_Id(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}