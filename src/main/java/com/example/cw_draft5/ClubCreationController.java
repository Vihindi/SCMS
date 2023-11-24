package com.example.cw_draft5;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class ClubCreationController {

    @FXML
    private Button CustomBtn;

    @FXML
    private TextField clubID;

    @FXML
    private TextField clubAdvisorID;

    @FXML
    private TextField clubBenefits;

    @FXML
    private TextField clubDescription;

    @FXML
    private TextField clubMission;

    @FXML
    private TextField clubName;

    @FXML
    private DatePicker startDate;

    @FXML
    void CreateClub(MouseEvent event) throws ClassNotFoundException {
        Club newClub = new Club(
                Integer.parseInt(clubID.getText()),
                clubName.getText(),
                clubDescription.getText(),
                clubMission.getText(),
                startDate.getValue(),
                Integer.parseInt(clubAdvisorID.getText()),
                clubBenefits.getText()
        );

        ClubCreationDatabase.AddClubToDB(newClub);

        // Show a success message to the user
        showSuccessAlert();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Club created successfully!");
        alert.showAndWait();
    }
}