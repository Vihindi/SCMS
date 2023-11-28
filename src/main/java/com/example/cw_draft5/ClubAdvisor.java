package com.example.cw_draft5;

public class ClubAdvisor {
    private int clubAdvisorID;
    private String clubAdvisorName;
    private String email;
    private String contactNo;
    private String gender;
    private String password;

    public ClubAdvisor(int clubAdvisorID,String clubAdvisorName, String email, String contactNo, String gender, String password) {
        this.clubAdvisorID = clubAdvisorID;
        this.clubAdvisorName = clubAdvisorName;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.password = password;
    }
    public ClubAdvisor(String clubAdvisorName, String email, String contactNo, String gender, String password) {
        this.clubAdvisorName = clubAdvisorName;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.password = password;
    }
    public ClubAdvisor(){

    }

    public String getClubAdvisorName() {
        return clubAdvisorName;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setClubAdvisorName(String clubAdvisorName) {
        this.clubAdvisorName = clubAdvisorName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClubAdvisorID() {
        return clubAdvisorID;
    }

    public void setClubAdvisorID(int clubAdvisorID) {
        this.clubAdvisorID = clubAdvisorID;
    }
}