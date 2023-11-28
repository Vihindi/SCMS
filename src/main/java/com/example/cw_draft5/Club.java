package com.example.cw_draft5;

import java.time.LocalDate;

public class Club {

    private  int clubID;
    private  String clubName;
    private  String clubDescription;
    private  String clubMission;
    private  LocalDate startDate;
    private  int clubAdvisorID;
    private  String clubBenefits;

    public Club(int clubID, String clubName, String clubDescription, String clubBenefits) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubBenefits = clubBenefits;
    }

    public Club(int clubID, String clubName, String clubDescription, String clubMission, LocalDate startDate, Integer clubAdvisorID, String clubBenefits) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubMission = clubMission;
        this.startDate = startDate;
        this.clubAdvisorID = clubAdvisorID;
        this.clubBenefits = clubBenefits;
    }


    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public  String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public  String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public  String getClubMission() {
        return clubMission;
    }

    public void setClubMission(String clubMission) {
        this.clubMission = clubMission;
    }

    public  LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public  int getClubAdvisorID() {
        return clubAdvisorID;
    }

    public void setClubAdvisorID(int clubAdvisorID) {
        this.clubAdvisorID = clubAdvisorID;
    }

    public  String getClubBenefits() {
        return clubBenefits;
    }

    public void setClubBenefits(String clubBenefits) {
        this.clubBenefits = clubBenefits;
    }
}