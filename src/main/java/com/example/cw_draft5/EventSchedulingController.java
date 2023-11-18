package com.example.cw_draft5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class EventSchedulingController {
    @FXML
    private TextField eventName;

    @FXML
    private Button submit;

    @FXML
    private TextField eventName1;

    @FXML
    private Button viewCalendar;

    @FXML
    private ChoiceBox<String> myChoiceBox;






    @FXML
    void viewCalendar(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Calendar.fxml")));
        stage.setScene(new Scene(root, 789, 531));
    }
}
