package com.example.cw_draft5;

import java.time.LocalDate;

public class Club extends DisplayClubs {

    public static Integer clubID;
    public static String clubName;
    public static String clubDescription;
    public static String clubMission;
    public static LocalDate startDate;
    public static Integer clubAdvisorID;
    public static String clubBenefits;

    public Club(int clubID, String clubName, String clubDescription, String clubBenefits) {
        Club.clubID = clubID;
        Club.clubName = clubName;
        Club.clubDescription = clubDescription;
        Club.clubBenefits = clubBenefits;
    }

    public Club(int clubID, String clubName, String clubDescription, String clubMission, LocalDate startDate, Integer clubAdvisorID, String clubBenefits) {
        Club.clubID = clubID;
        Club.clubName = clubName;
        Club.clubDescription = clubDescription;
        Club.clubMission = clubMission;
        Club.startDate = startDate;
        Club.clubAdvisorID = clubAdvisorID;
        Club.clubBenefits = clubBenefits;
    }
    public static int getClubID(){
        return clubID;
    }

    public static String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        Club.clubName = clubName;
    }

    public static String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        Club.clubDescription = clubDescription;
    }

    public static String getClubMission() {
        return clubMission;
    }

    public void setClubMission(String clubMission) {
        Club.clubMission = clubMission;
    }

    public static LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        Club.startDate = startDate;
    }

    public static Integer getClubAdvisorID() {
        return clubAdvisorID;
    }

    public static String getClubBenefits() {
        return clubBenefits;
    }

    public void setClubBenefits(String clubBenefits) {
        Club.clubBenefits = clubBenefits;
    }
}