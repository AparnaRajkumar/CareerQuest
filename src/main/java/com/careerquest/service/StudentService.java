package com.careerquest.service;
import com.careerquest.repository.StudentPerformanceRepository;
import com.careerquest.model.StudentPerformance;
import com.careerquest.dto.StudentRegistrationRequest;
import com.careerquest.model.Student;

import com.careerquest.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class StudentService {
     private final StudentPerformanceRepository studentPerformanceRepository;
    private final StudentRepository studentRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

   public StudentService(
        StudentRepository studentRepository,
        StudentPerformanceRepository studentPerformanceRepository) {

    this.studentRepository = studentRepository;
    this.studentPerformanceRepository = studentPerformanceRepository;
}

    // POST - Register Student
    public Student registerStudent(StudentRegistrationRequest request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                    "Email already registered");
        }

        Student student = new Student();

        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());

        student.setPasswordHash(
                passwordEncoder.encode(request.getPassword()));

        student.setDepartment(request.getDepartment());
        student.setGraduationYear(request.getGraduationYear());

        Student savedStudent = studentRepository.save(student);

// Create default performance record
StudentPerformance performance = new StudentPerformance();

performance.setStudent(savedStudent);
performance.setCodingScore(0);
performance.setAptitudeScore(0);
performance.setInterviewScore(0);
performance.setXpPoints(0);
performance.setLevel(1);
performance.setPlacementReadiness(0);

studentPerformanceRepository.save(performance);

return savedStudent;
    }

    // GET - View All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET - Find Student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Student not found"));
    }

    // PUT - Update Student
    public Student updateStudent(Long id, Student updatedStudent) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Student not found"));

        student.setFullName(updatedStudent.getFullName());
        student.setEmail(updatedStudent.getEmail());
        student.setDepartment(updatedStudent.getDepartment());
        student.setGraduationYear(updatedStudent.getGraduationYear());

        return studentRepository.save(student);
    }

    // DELETE - Delete Student
    public void deleteStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Student not found"));

        studentRepository.delete(student);
    }

    // POST - Student Login
    public Student loginStudent(String email, String password) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Invalid email or password"));

        if (!passwordEncoder.matches(
                password, student.getPasswordHash())) {

            throw new IllegalArgumentException(
                    "Invalid email or password");
        }

        return student;
    }
}