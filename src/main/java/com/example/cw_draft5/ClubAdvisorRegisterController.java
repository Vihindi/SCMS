package com.example.cw_draft5;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

public class ClubAdvisorRegisterController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    public TextField clubAdvisorName;
    @FXML
    public TextField clubAdvisorContact;
    @FXML
    public TextField clubAdvisorGender;
    @FXML
    public TextField clubAdvisorEmail;
    @FXML
    public PasswordField clubAdvisorPassword;

    PreparedStatement pst;
    ClubAdvisor clubAdvisor;


    private void displayErrorMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.show();

    }

    public void onClickClubAdvisorRegister(ActionEvent event) {
        try {
            clubAdvisor = new ClubAdvisor(clubAdvisorName.getText(), clubAdvisorEmail.getText(), clubAdvisorContact.getText(), clubAdvisorGender.getText(), clubAdvisorPassword.getText());
            if (!validateClubAdvisorName() || !validateEmail() || !validateContactNo() || !validateGender() || !validateStudentPassword()) {
                return;}

            pst = DatabaseConnection.getConnection().prepareStatement("INSERT INTO clubadvisor (FullName, Email, ContactNo, Gender, setPassword) VALUES (?,?,?,?,?)");
            pst.setString(1, clubAdvisor.getClubAdvisorName());
            pst.setString(2, clubAdvisor.getEmail());
            pst.setString(3, clubAdvisor.getContactNo());
            pst.setString(4, clubAdvisor.getGender());
            pst.setString(5, clubAdvisor.getPassword());

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enter all the information");
                alert.showAndWait();
            }

        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter the correct student number");
            alert.showAndWait();

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateClubAdvisorName() {
        if (clubAdvisor.getClubAdvisorName().isEmpty()) {
            displayErrorMessage("Please enter the correct name");
            clubAdvisorName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
        for (char a: clubAdvisor.getClubAdvisorName().toCharArray())
            if (Character.isDigit(a)){
                displayErrorMessage("Please do not enter any numbers");
                clubAdvisorName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                return false;
            }
        return true;
    }

    private boolean validateEmail(){
        String email = clubAdvisorEmail.getText();

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            displayErrorMessage("Please enter a valid email address");
            clubAdvisorEmail.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }

        return true;
    }

    private boolean validateContactNo() {
        try {
            if (clubAdvisor.getContactNo().length() != 10) {
                System.out.println(clubAdvisor.getContactNo());
                displayErrorMessage("Please enter the correct contact number");
                return false;
            }
        } catch (NumberFormatException e) {
            displayErrorMessage("Please enter numbers only");
            return false;
        }
        return true;
    }

    private boolean validateGender(){
        String genderText = clubAdvisorGender.getText().toLowerCase();

        if (!"female".equals(genderText) && !"male".equals(genderText)) {
            displayErrorMessage("Please enter either 'female' or 'male' for gender");
            clubAdvisorGender.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }else{
            if(genderText.equals("female")){
                clubAdvisor.setGender("F");
            }else{
                clubAdvisor.setGender("M");
            }
        }

        return true;
    }

    private boolean validateStudentPassword(){
        if(clubAdvisor.getPassword().isEmpty()){
            displayErrorMessage("Please set a strong password for your club profile");
            return false;
        }
        return true;
    }

}
