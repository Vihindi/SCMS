package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EventRegistrationController {

    @FXML
    private ComboBox<String> ClubName;

    @FXML
    private Button CustomBtn;

    @FXML
    private ComboBox<String> EventName;


    @FXML
    private TextField studentID;

    private AttendanceQuaries attendanceQuaries;

    @FXML
    void initialize(){
        attendanceQuaries = new AttendanceQuaries();
        ClubName.setItems(attendanceQuaries.getClubNames());
    }

    @FXML
    void EventRegistrationClick(MouseEvent event) {
    String StudentID = studentID.getText();
    String clubName = ClubName.getValue();
    String eventName = EventName.getValue();
    attendanceQuaries.getEventregistration(clubName,eventName,StudentID);
    }

    @FXML
    void ClubClick(ActionEvent event) {
        String clubName = ClubName.getValue();
        EventName.setItems(attendanceQuaries.getEventNames(clubName));
    }


}
