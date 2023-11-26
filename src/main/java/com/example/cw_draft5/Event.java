package com.example.cw_draft5;

import java.time.LocalDate;

public class Event {
    private String ClubName;

    private int ClubID;
    private int EventID;
    private String EventName;

    private LocalDate Date;
    private String EventCode;
    private String Description;
    private String Mode;
    private String Venue;
    private String TimeSlot;

    public Event() {
        this.ClubID=ClubID;
        this.EventID=EventID;
        this.EventName = EventName;
        this.Date = (Date != null) ? Date : LocalDate.now();
        this.EventCode = EventCode;
        this.Mode = Mode;
        this.Venue = Venue;
        this.TimeSlot = TimeSlot;
        this.Description = Description;

    }

    public Event(int clubID, int eventID, String eventName, LocalDate date, String eventCode, String mode, String venue, String timeSlot, String description) {
        this.ClubID = clubID;
        this.EventID = eventID;
        this.EventName = eventName;
        this.Date = (Date != null) ? Date : LocalDate.now();
        this.EventCode = eventCode;
        this.Mode = mode;
        this.Venue = venue;
        this.TimeSlot = timeSlot;
        this.Description = description;
    }



    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String clubName) {
        ClubName = clubName;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int clubID) {
        ClubID = clubID;
    }


    public int getEventID() {
        return EventID;
    }


    public void setEventID(int eventID) {
        EventID = eventID;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getEventCode() {
        return EventCode;
    }

    public void setEventCode(String eventCode) {
        EventCode = eventCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMode() {
        return Mode;
    }

    public void setMode(String mode) {
        Mode = mode;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getTimeSlot() {
        return TimeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        TimeSlot = timeSlot;
    }











}
