package com.example.cw_draft5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EventSchedulingController {

    @FXML
    private TextField ClubID;

    @FXML
    private TextField EventID;

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
    private Button submit;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    void viewCalendar(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Calendar.fxml")));
        stage.setScene(new Scene(root, 789, 531));
    }

    @FXML
    void submitClicked(MouseEvent event) {
        Event newEvent = new Event(
                Integer.parseInt(ClubID.getText()),
                Integer.parseInt(EventID.getText()),
                EventName.getText(),
                Date.getValue(),
                EventCode.getText(),
                Mode.getValue(),
                Venue.getText(),
                TimeSlot.getText(),
                Description.getText()
        );

        AddEventIntoDatabase.addToDatabase(newEvent);

        // Show a success message to the user
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
