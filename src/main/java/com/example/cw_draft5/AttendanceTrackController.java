package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AttendanceTrackController {

    @FXML
    private TableView <AttendanceDatabase> AttendanceTable;

    @FXML
    private TableColumn<AttendanceDatabase, String> NameColumn;

    @FXML
    private TableColumn<AttendanceDatabase, Integer> studentIDColumn;

    @FXML
    private AnchorPane ap;

    @FXML
    private ComboBox<String> clubComboBox;

    @FXML
    private ComboBox<String> eventComboBox;

    @FXML
    private Text textId;

    private AttendanceDatabase attendanceDatabase;

    @FXML
    void initialize() {
        attendanceDatabase = new AttendanceDatabase();
        populateClubComboBox();


    }

    private void populateClubComboBox() {
        clubComboBox.setItems(attendanceDatabase.getClubNames());
    }


    @FXML
     void ClickClubSearch(ActionEvent event) {
        String selectedClub = clubComboBox.getValue();
        eventComboBox.setItems(attendanceDatabase.getEventNames(selectedClub));
    }

    @FXML
    void ClickEventSearch(ActionEvent event) {

    }


}
