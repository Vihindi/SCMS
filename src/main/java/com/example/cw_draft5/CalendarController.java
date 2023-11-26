package com.example.cw_draft5;

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


}
