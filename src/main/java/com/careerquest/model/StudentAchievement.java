package com.careerquest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_achievements")
public class StudentAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String title;

    private String description;

    public StudentAchievement() {}

    public Long getId() { return id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}