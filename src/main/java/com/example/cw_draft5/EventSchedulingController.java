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
        String mode = Mode.getValue();
        String venue = Venue.getText();
        String timeSlot = TimeSlot.getText();
        String description = Description.getText();
        String selectedClub = ClubName.getValue();

        // Validate time slot format
        if (!isValidTimeSlot(timeSlot)) {
            // Show an error alert
            showErrorAlert("Invalid Time Slot", "Time slot format should be in the form of HH:mmAM/PM-HH:mmAM/PM(8.00AM-10.00AM)");
            return;
        }

        // Create an Event object
        Event eventToAdd = new Event();

        // Set properties of the Event object
        eventToAdd.setEventName(eventName);
        eventToAdd.setDate(eventDate);
        eventToAdd.setEventCode(eventCode);
        eventToAdd.setMode(mode);
        eventToAdd.setVenue(venue);
        eventToAdd.setTimeSlot(timeSlot);
        eventToAdd.setDescription(description);

        // Add the event to the database
        AddEventIntoDatabase.addToDatabase(eventToAdd, selectedClub);

        // Show a success alert
        showSuccessAlert();
    }

    private boolean isValidTimeSlot(String timeSlot) {
        // Regular expression for validating time slot format
        String timeSlotRegex = "\\d{1,2}.\\d{2}[APMapm]{2}-\\d{1,2}.\\d{2}[APMapm]{2}";

        // Check if the time slot matches the pattern
        return timeSlot.matches(timeSlotRegex);
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Event created successfully!");
        alert.showAndWait();
    }
}

