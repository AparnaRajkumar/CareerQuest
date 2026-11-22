package com.careerquest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_performance")
public class StudentPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Student student;

    private int codingScore;
    private int aptitudeScore;
    private int interviewScore;
    private int xpPoints;
    private int level;
    private int placementReadiness;

    public StudentPerformance() {}

    public Long getId() { return id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCodingScore() { return codingScore; }
    public void setCodingScore(int score) {
        this.codingScore = score;
    }

    public int getAptitudeScore() { return aptitudeScore; }
    public void setAptitudeScore(int score) {
        this.aptitudeScore = score;
    }

    public int getInterviewScore() { return interviewScore; }
    public void setInterviewScore(int score) {
        this.interviewScore = score;
    }

    public int getXpPoints() { return xpPoints; }
    public void setXpPoints(int xp) {
        this.xpPoints = xp;
    }

    public int getLevel() { return level; }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getPlacementReadiness() {
        return placementReadiness;
    }

    public void setPlacementReadiness(int readiness) {
        this.placementReadiness = readiness;
    }
}