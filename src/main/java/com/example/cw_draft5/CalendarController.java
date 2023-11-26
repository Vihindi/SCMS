package com.example.cw_draft5;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CalendarController {

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, Integer> clubID;

    @FXML
    private TableColumn<Event, Integer> eventID;

    @FXML
    private TableColumn<Event, String> eventName;

    @FXML
    private TableColumn<Event, LocalDate> date;

    @FXML
    private TableColumn<Event, String> eventCode;

    @FXML
    private TableColumn<Event, String> description;

    @FXML
    private TableColumn<Event, String> mode;

    @FXML
    private TableColumn<Event, String> venue;

    @FXML
    private TableColumn<Event, String> timeSlot;

    @FXML
    private Button reschedule;

    @FXML
    private Button rechedule;

    @FXML
    private Text upcomingEventsText;

    private ObservableList<Event> events = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        // Set up the columns in the TableView
        clubID.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        eventID.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        eventName.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        eventCode.setCellValueFactory(new PropertyValueFactory<>("eventCode"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        mode.setCellValueFactory(new PropertyValueFactory<>("mode"));
        venue.setCellValueFactory(new PropertyValueFactory<>("venue"));
        timeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));

        // Load events from the database and populate the TableView
        loadEventsFromDatabase();
        // Enable row selection in the TableView
        eventTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Handle row selection events
        eventTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Enable edit buttons when a row is selected
                reschedule.setDisable(false);
                // rechedule.setDisable(false); // Note: There's a typo in your FXML for this button
            } else {
                // Disable edit buttons when no row is selected
                reschedule.setDisable(true);
                // rechedule.setDisable(true);
            }
        });
    }

    private void loadEventsFromDatabase() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM eventscheduling";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int clubID = resultSet.getInt("clubID");
                    int eventID = resultSet.getInt("eventID");
                    String eventName = resultSet.getString("eventName");
                    LocalDate date = resultSet.getDate("eventDate").toLocalDate();
                    String eventCode = resultSet.getString("eventCode");
                    String description = resultSet.getString("description");
                    String mode = resultSet.getString("mode");
                    String venue = resultSet.getString("venue");
                    String timeSlot = resultSet.getString("timeSlot");

                    Event event = new Event(clubID, eventID, eventName, date, eventCode, mode, venue, timeSlot, description);

                    // Add the created event to the observable list
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }

        // Set the items in the TableView
        eventTable.setItems(events);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean isClubAdvisor(String username) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM clubadvisor WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0; // If count is greater than 0, the email exists, so it's a valid club advisor
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
        return false;
    }


    @FXML
    void deleteClicked() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Club Advisor Verification");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your username:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String username = result.get();
            if (isClubAdvisor(username)) {
                Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();

                if (selectedEvent != null) {
                    // Remove from the TableView
                    events.remove(selectedEvent);
                    eventTable.setItems(events);

                    // Remove from the Database
                    deleteEventFromDatabase(selectedEvent);

                    showAlert("Event Deleted", "The selected event has been deleted successfully.");
                } else {
                    showAlert("No Event Selected", "Please select an event to delete.");
                }
            } else {
                showAlert("Invalid Club Advisor", "The entered username does not match any club advisor.");
            }
        }
    }

    private void deleteEventFromDatabase(Event event) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM eventscheduling WHERE eventID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, event.getEventID());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Event deleted from the database.");
                } else {
                    System.out.println("Event not found in the database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }

    //reschedule
    @FXML
    void rescheduleClicked() {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();

        if (selectedEvent != null) {
            // Show a dialog to edit the event details
            boolean edited = showEditEventDialog(selectedEvent);

            if (edited) {
                // Update the TableView with the edited event
                eventTable.refresh();
                showAlert("Event Updated", "The selected event has been updated successfully.");
            }
        } else {
            showAlert("No Event Selected", "Please select an event to edit.");
        }
    }

    private boolean showEditEventDialog(Event event) {
        TextInputDialog emailDialog = new TextInputDialog();
        emailDialog.setTitle("Club Advisor Verification");
        emailDialog.setHeaderText(null);
        emailDialog.setContentText("Enter your email:");

        Optional<String> emailResult = emailDialog.showAndWait();

        if (emailResult.isPresent() && isClubAdvisor(emailResult.get())) {
            Dialog<Map<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Edit Event");
            dialog.setHeaderText("Edit the event details:");

            // Set the button types
            ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

            // Create form elements
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField eventNameField = new TextField(event.getEventName());
            DatePicker dateDatePicker = new DatePicker(event.getDate());
            TextField modeField = new TextField(event.getMode());
            TextField venueField = new TextField(event.getVenue());
            TextField timeSlotField = new TextField(event.getTimeSlot());
            TextField eventCodeField = new TextField(event.getEventCode());
            TextArea descriptionField = new TextArea(event.getDescription());

            grid.add(new Label("Event Name:"), 0, 0);
            grid.add(eventNameField, 1, 0);
            grid.add(new Label("Event Date:"), 0, 1);
            grid.add(dateDatePicker, 1, 1);
            grid.add(new Label("Mode:"), 0, 2);
            grid.add(modeField, 1, 2);
            grid.add(new Label("Venue:"), 0, 3);
            grid.add(venueField, 1, 3);
            grid.add(new Label("Time Slot:"), 0, 4);
            grid.add(timeSlotField, 1, 4);
            grid.add(new Label("Event Code:"), 0, 5);
            grid.add(eventCodeField, 1, 5);
            grid.add(new Label("Description:"), 0, 6);
            grid.add(descriptionField, 1, 6);

            dialog.getDialogPane().setContent(grid);

            // Request focus on the event name field by default
            Platform.runLater(() -> eventNameField.requestFocus());

            // Convert the result to a map when the save button is clicked
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButtonType) {
                    Map<String, String> resultMap = new HashMap<>();
                    resultMap.put("eventName", eventNameField.getText());
                    resultMap.put("eventDate", dateDatePicker.getValue().toString());
                    resultMap.put("mode", modeField.getText());
                    resultMap.put("venue", venueField.getText());
                    resultMap.put("timeSlot", timeSlotField.getText());
                    resultMap.put("eventCode", eventCodeField.getText());
                    resultMap.put("description", descriptionField.getText());
                    return resultMap;
                }
                return null;
            });

            Optional<Map<String, String>> result = dialog.showAndWait();

            result.ifPresent(fieldValues -> {
                // Update the event with the edited details
                event.setEventName(fieldValues.get("eventName"));
                event.setDate(LocalDate.parse(fieldValues.get("eventDate")));
                event.setMode(fieldValues.get("mode"));
                event.setVenue(fieldValues.get("venue"));
                event.setTimeSlot(fieldValues.get("timeSlot"));
                event.setEventCode(fieldValues.get("eventCode"));
                event.setDescription(fieldValues.get("description"));

                // Update the event in the database
                updateEventInDatabase(event);
            });

            return result.isPresent();
        } else {
            showAlert("Invalid Club Advisor", "The entered email does not match any club advisor.");
            return false;
        }
    }

    private void updateEventInDatabase(Event event) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "UPDATE eventscheduling SET eventName = ?, eventDate = ? WHERE eventID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, event.getEventName());
                statement.setDate(2, java.sql.Date.valueOf(event.getDate()));
                statement.setInt(3, event.getEventID());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Event updated in the database.");
                } else {
                    System.out.println("Event not found in the database.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }


}
