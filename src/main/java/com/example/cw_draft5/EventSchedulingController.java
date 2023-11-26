package com.example.cw_draft5;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class EventSchedulingController {



    @FXML
    private TextField EventName;

    @FXML
    private DatePicker Date;

    @FXML
    private TextField EventCode;

    @FXML
    private TextArea Description;

    @FXML
    private ChoiceBox<String> Mode;

    @FXML
    private TextField Venue;

    @FXML
    private TextField TimeSlot;

    @FXML
    private ComboBox<String> ClubName;

    @FXML
    private Button submit;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private AttendanceDatabase attendanceDatabase;

    @FXML
    void initialize() {
        attendanceDatabase = new AttendanceDatabase();
        populateClubNames();
    }

    private void populateClubNames() {
        ClubName.setItems(attendanceDatabase.getClubNames());
    }

    @FXML
    void submitClicked(MouseEvent event) {
        // Retrieve values from UI components
        String eventName = EventName.getText();
        LocalDate eventDate = Date.getValue();
        String eventCode = EventCode.getText();
        String mode = Mode.getValue(); // Assuming Mode is a ChoiceBox or similar
        String venue = Venue.getText();
        String timeSlot = TimeSlot.getText();
        String description = Description.getText();
        String selectedClub = ClubName.getValue();

        // Create an Event object
        Event eventToAdd = new Event();

        // Add the event to the database
        AddEventIntoDatabase.addToDatabase(eventToAdd, selectedClub);

        // Show a success alert
        showSuccessAlert();
    }


    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Event created successfully!");
        alert.showAndWait();
    }
}
