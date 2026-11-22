package com.careerquest.repository;

import com.careerquest.model.StudentAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAchievementRepository
        extends JpaRepository<StudentAchievement, Long> {

    boolean existsByStudent_IdAndTitle(
        Long studentId, String title);
}