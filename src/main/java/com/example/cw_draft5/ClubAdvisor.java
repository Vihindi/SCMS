package com.example.cw_draft5;

public class ClubAdvisor {
    private String clubAdvisorName;
    private String email;
    private String contactNo;
    private String gender;
    private String password;

    public ClubAdvisor(String clubAdvisorName, String email, String contactNo, String gender, String password) {
        this.clubAdvisorName = clubAdvisorName;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.password = password;
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
}
