package com.careerquest.dto;

public class LoginResponse {

    private String message;
    private Long studentId;
    private String fullName;
    private String email;

    public LoginResponse(String message, Long studentId,
                         String fullName, String email) {

        this.message = message;
        this.studentId = studentId;
        this.fullName = fullName;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}