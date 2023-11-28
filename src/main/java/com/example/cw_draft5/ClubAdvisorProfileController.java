package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubAdvisorProfileController {

    public Label clubAdvisorDisplayID;
    @FXML
    private TextField clubAdvisorChangePassword;

    @FXML
    private TextField clubAdvisorContactDisplay;

    @FXML
    private Label clubAdvisorDisplayName;

    @FXML
    private TextField clubAdvisorEmailDisplay;
    private ClubAdvisor clubAdvisor;
    PreparedStatement pst;
    ResultSet rs;


    public TextField getClubAdvisorChangePassword() {
        return clubAdvisorChangePassword;
    }

    public void setClubAdvisorChangePassword(TextField clubAdvisorChangePassword) {
        this.clubAdvisorChangePassword = clubAdvisorChangePassword;
    }

    public TextField getClubAdvisorContactDisplay() {
        return clubAdvisorContactDisplay;
    }

    public void setClubAdvisorContactDisplay(TextField clubAdvisorContactDisplay) {
        this.clubAdvisorContactDisplay = clubAdvisorContactDisplay;
    }

    public Label getClubAdvisorDisplayName() {
        return clubAdvisorDisplayName;
    }

    public void setClubAdvisorDisplayName(Label clubAdvisorDisplayName) {
        this.clubAdvisorDisplayName = clubAdvisorDisplayName;
    }

    public TextField getClubAdvisorEmailDisplay() {
        return clubAdvisorEmailDisplay;
    }

    public void setClubAdvisorEmailDisplay(TextField clubAdvisorEmailDisplay) {
        this.clubAdvisorEmailDisplay = clubAdvisorEmailDisplay;
    }

    @FXML
    void onClickSaveChanges(ActionEvent event) {
        try {
            String newEmail = clubAdvisorEmailDisplay.getText();
            String newContactNo = clubAdvisorContactDisplay.getText();
            String newPassword = clubAdvisorChangePassword.getText();

            clubAdvisor.setEmail(newEmail);
            clubAdvisor.setContactNo(newContactNo);
            clubAdvisor.setPassword(newPassword);

            pst = DatabaseConnection.getConnection().prepareStatement("UPDATE clubadvisor SET Email = ?, ContactNo = ?, Password = ? WHERE ClubAdvisorID = ?");
            pst.setString(1, newEmail);
            pst.setString(2, newContactNo);
            pst.setString(3, newPassword);
            pst.setInt(4, clubAdvisor.getClubAdvisorID());

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setClubAdvisor(ClubAdvisor clubAdvisor){
        this.clubAdvisor = clubAdvisor;
        if(clubAdvisorDisplayName!=null){
            displayClubAdvisorProfile();
        }
    }

    public void initialize() {
        if(clubAdvisor!=null){
            displayClubAdvisorProfile();
        }
    }

    private void displayClubAdvisorProfile(){
        if(clubAdvisor != null){
            clubAdvisorDisplayName.setText(clubAdvisor.getClubAdvisorName());
            clubAdvisorEmailDisplay.setText(clubAdvisor.getEmail());
            clubAdvisorContactDisplay.setText(clubAdvisor.getContactNo());
            clubAdvisorChangePassword.setText(clubAdvisor.getPassword());
        }
    }
}
