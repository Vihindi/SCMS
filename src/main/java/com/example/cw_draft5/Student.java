package com.example.cw_draft5;

import java.time.LocalDate;

public class Student {
    private String studentID;
    private String fullName;
    private LocalDate dateOfBirth;
    private String contactNo;
    private String gender;
    private String email;
    private String location;
    private String grade;
    private String guardianName;
    private String guardianContactNo;
    private String skills;
    private String password;

    public Student(String studentID, String fullName, LocalDate dateOfBirth, String contactNo, String gender, String email, String location, String grade, String guardianName, String guardianContactNo, String skills, String password) {
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
    public Student(){

    }
    public Student(String studentID,String fullName){
        this.studentID = studentID;
        this.fullName = fullName;
    }



    public String getStudentID() {
        return studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


    public String getContactNo() {
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


    public String getGrade() {
        return grade;
    }


    public String getGuardianName() {
        return guardianName;
    }


    public String getGuardianContactNo() {
        return guardianContactNo;
    }


    public String getSkills() {
        return skills;
    }


    public String getPassword() {
        return password;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public void setGuardianContactNo(String guardianContactNo) {
        this.guardianContactNo = guardianContactNo;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
