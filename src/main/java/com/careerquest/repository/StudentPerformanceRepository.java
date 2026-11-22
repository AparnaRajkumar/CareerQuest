package com.careerquest.repository;

import com.careerquest.model.StudentPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentPerformanceRepository
        extends JpaRepository<StudentPerformance, Long> {

    Optional<StudentPerformance> findByStudent_Id(Long studentId);
}