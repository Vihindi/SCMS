package com.example.cw_draft5;

import java.time.LocalDate;

public class Student {
    private String studentID;
    private String fullName;
    private LocalDate dateOfBirth;
    private int contactNo;
    private String gender;
    private String email;
    private String location;
    private int grade;
    private String guardianName;
    private int guardianContactNo;
    private String skills;
    private String password;

    public Student(String studentID, String fullName, LocalDate dateOfBirth, int contactNo, String gender, String email, String location, int grade, String guardianName, int guardianContactNo, String skills, String password) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.contactNo = contactNo;
        this.gender = gender;
        this.email = email;
        this.location = location;
        this.grade = grade;
        this.guardianName = guardianName;
        this.guardianContactNo = guardianContactNo;
        this.skills = skills;
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDateOfBirth() {
        return String.valueOf(dateOfBirth);
    }


    public int getContactNo() {
        return contactNo;
    }

    public String getGender() {
        return gender;
    }


    public String getEmail() {
        return email;
    }


    public String getLocation() {
        return location;
    }


    public int getGrade() {
        return grade;
    }


    public String getGuardianName() {
        return guardianName;
    }


    public int getGuardianContactNo() {
        return guardianContactNo;
    }


    public String getSkills() {
        return skills;
    }


    public String getPassword() {
        return password;
    }

}
