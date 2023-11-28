package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class LoginController extends DatabaseConnection {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Label validLogin;
    @FXML
    public Button loginButton;
    @FXML
    public Text loginResult;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    PreparedStatement pst;
    ResultSet rs;


    @FXML
    public void onClickCreateAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainRegisterPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickLogin(ActionEvent event) throws IOException {
         String username = usernameField.getText();
         String password = passwordField.getText();

        if (username.equals("") && password.equals("")) {
            loginResult.setText("Please enter the username and password");

        } else {
            try {
                pst = DatabaseConnection.getConnection().prepareStatement("SELECT FullName,Email,ContactNo,Password FROM student WHERE Email=? AND Password=?" +
                        " UNION ALL " +
                        "SELECT FullName,Email,ContactNo,Password FROM clubadvisor WHERE Email=? AND Password=?");
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3,username);
                pst.setString(4, password);

                rs = pst.executeQuery();

                if (rs.next()) {
                    ClubAdvisor clubAdvisor = new ClubAdvisor();
                    clubAdvisor.setClubAdvisorName(rs.getString("FullName"));
                    clubAdvisor.setEmail(rs.getString("Email"));
                    clubAdvisor.setContactNo(rs.getString("ContactNo"));
                    clubAdvisor.setPassword(rs.getString("Password"));

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                    Parent root = loader.load();
                    DashboardController dashboardController = loader.getController();
                    dashboardController.setClubAdvisor(clubAdvisor);

                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    loginResult.setText("Entered incorrect email or password");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }
//    private void loadStudentAdditionalInfo(ClubAdvisor clubAdvisor) {
//        try {
//            PreparedStatement infoPst = DatabaseConnection.getConnection().prepareStatement(
//                    "SELECT FullName FROM club WHERE Email=?");
//            infoPst.setString(1, clubAdvisor.getEmail()); // Assuming getEmail method in Student class
//
//            ResultSet infoRs = infoPst.executeQuery();
//
//            if (infoRs.next()) {
//                // Set additional fields in the Student object
//                clubAdvisor.setClubAdvisorName(infoRs.getString("FullName"));
//                // Add other fields as needed
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
