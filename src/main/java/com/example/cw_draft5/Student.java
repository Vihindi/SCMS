package com.example.cw_draft5;

public class Student {
    private String studentID;
    private String fullName;

    public Student(String studentID, String fullName) {
        this.studentID = studentID;
        this.fullName = fullName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
