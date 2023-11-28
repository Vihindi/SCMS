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
    void CreateClub(MouseEvent event) {
        try {
            int newClubID = Integer.parseInt(clubID.getText());
            String newClubName = clubName.getText();

            // Check for duplicate ClubID
            if (ClubCreationDatabase.isClubIDExists(newClubID)) {
                showAlert("Error", "Duplicate Club ID", "Club ID already exists. Please enter a different ID.");
                return;
            }

            // Check for duplicate ClubName
            if (ClubCreationDatabase.isClubNameExists(newClubName)) {
                showAlert("Error", "Duplicate Club Name", "Club Name already exists. Please enter a different name.");
                return;
            }

            // If no duplicates, create the new club and add it to the database
            Club newClub = new Club(
                    newClubID,
                    newClubName,
                    clubDescription.getText(),
                    clubMission.getText(),
                    startDate.getValue(),
                    Integer.parseInt(clubAdvisorID.getText()),
                    clubBenefits.getText()
            );

            ClubCreationDatabase.AddClubToDB(newClub);

            // Show a success message to the user
            showSuccessAlert();
            clearTextFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid Input", "Please enter valid numeric values for Club ID.");
        } catch (Exception e) {
            showAlert("Error", "Duplication Error", "Please try again.");
        }
    }


    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Club created successfully!");
        alert.showAndWait();
    }

    private void clearTextFields() {
        clubID.clear();
        clubName.clear();
        clubDescription.clear();
        clubMission.clear();
        startDate.setValue(null);
        clubAdvisorID.clear();
        clubBenefits.clear();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

