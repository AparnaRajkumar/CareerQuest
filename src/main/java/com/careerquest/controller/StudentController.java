package com.careerquest.controller;

import com.careerquest.dto.StudentRegistrationRequest;
import com.careerquest.model.Student;
import com.careerquest.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;
import com.careerquest.dto.LoginRequest;
import com.careerquest.dto.LoginResponse;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // POST - Register Student
    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(
            @Valid @RequestBody StudentRegistrationRequest request) {

        try {
            Student student = studentService.registerStudent(request);

            // Never send password information to frontend
            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                    "message", "Registration successful!",
                    "studentId", student.getId(),
                    "fullName", student.getFullName(),
                    "email", student.getEmail()
                )
            );

        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", exception.getMessage()));
        }
    }

    // GET - View All Students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(
            studentService.getAllStudents()
        );
    }
    // GET - Find Student by ID
@GetMapping("/{id}")
public ResponseEntity<Student> getStudentById(
        @PathVariable Long id) {

    return ResponseEntity.ok(
            studentService.getStudentById(id));
} 
// PUT - Update Student
@PutMapping("/{id}")
public ResponseEntity<Student> updateStudent(
        @PathVariable Long id,
        @RequestBody Student updatedStudent) {

    return ResponseEntity.ok(
            studentService.updateStudent(id, updatedStudent));
}
// DELETE - Delete Student
@DeleteMapping("/{id}")
public ResponseEntity<?> deleteStudent(
        @PathVariable Long id) {

    studentService.deleteStudent(id);

    return ResponseEntity.ok(
        Map.of("message", "Student deleted successfully!")
    );
}
// POST - Student Login
    @PostMapping("/login")
    public ResponseEntity<?> loginStudent(
            @RequestBody LoginRequest request) {

        try {
            Student student = studentService.loginStudent(
                    request.getEmail(),
                    request.getPassword()
            );

            return ResponseEntity.ok(
                    new LoginResponse(
                            "Login successful!",
                            student.getId(),
                            student.getFullName(),
                            student.getEmail()
                    )
            );

        } catch (IllegalArgumentException exception) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "message",
                            exception.getMessage()
                    ));
        }
    }


}