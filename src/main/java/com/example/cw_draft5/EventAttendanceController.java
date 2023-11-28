package com.example.cw_draft5;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class EventAttendanceController {

    @FXML
    private Button CustomBtn;

    @FXML
    private ComboBox<String> clubComboBox;

    @FXML
    private TextField eventCodeTextField;

    @FXML
    private ComboBox<String> eventComboBox;

    @FXML
    private Text textId;

    private AttendanceQuaries attendanceQuaries;



    protected static String username1;



    @FXML
    void initialize() {
        attendanceQuaries = new AttendanceQuaries();
        populateClubComboBox();

    }

    private void populateClubComboBox() {
        clubComboBox.setItems(attendanceQuaries.getClubNames());
    }

    @FXML
    void AttendClick(MouseEvent event) {
        String club = clubComboBox.getValue();
        String eventCode = eventCodeTextField.getText();
        String selectedEvent = eventComboBox.getValue();

        if (selectedEvent != null) {
            Boolean EventCode = attendanceQuaries.getEventCode(eventCode, selectedEvent);
            System.out.println(EventCode);
            System.out.println("Hii" + username1);
            attendanceQuaries.MarkAttendance(username1, club, selectedEvent);
        } else {
            // Handle the case where no event is selected
            System.out.println("Please select an event");
        }

    }



    @FXML
    void clubComboBoxAction(ActionEvent event) {
        String selectedClub = clubComboBox.getValue();
        eventComboBox.setItems(attendanceQuaries.getEventNames(selectedClub));
    }

    @FXML
    void eventComboBoxAction(ActionEvent event) {
    }



}