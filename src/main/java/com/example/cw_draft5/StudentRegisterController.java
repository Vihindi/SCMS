package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class StudentRegisterController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    public TextField classTeacherNo;
    public TextField guardianNo;
    @FXML
    private TextField userLocation;
    @FXML
    private TextField guardianName;

    @FXML
    private TextField classTeacherName;

    @FXML
    private TextField contactNo;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField fullName;

    @FXML
    private TextField genderType;

    @FXML
    private TextField grade;

    @FXML
    private PasswordField setPassword;

    @FXML
    private TextArea skills;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;


    @FXML
    void onClickStudentRegister(ActionEvent event) throws IOException {
        try {
            pst = DatabaseConnection.getConnection().prepareStatement("INSERT INTO Student (FullName, DOB, Contact, Gender, Email, Location, Grade, ClassTeacherName, ClassTeacherContact, GuardianName, GuardianContact, Skills, Password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, fullName.getText());
            pst.setString(2, String.valueOf(dateOfBirth.getValue()));
            pst.setString(3, contactNo.getText());
            pst.setString(4, genderType.getText());
            pst.setString(5, emailAddress.getText());
            pst.setString(6, userLocation.getText());
            pst.setString(7, grade.getText());
            pst.setString(8, classTeacherName.getText());
            pst.setString(9, classTeacherNo.getText());
            pst.setString(10, guardianName.getText());
            pst.setString(11, guardianNo.getText());
            pst.setString(12, skills.getText());
            pst.setString(13, setPassword.getText());

            int affectedRows = pst.executeUpdate();

            if (affectedRows>0) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


//if (username.equals("") && password.equals("")) {
//        loginResult.setText("Please enter the username and password");
//
//        } else {
//        try {
//        Class.forName("com.mysql.jdbc.Driver");
//        con = DriverManager.getConnection("jdbc:mysql://localhost/SACMS", "root", "");
//        pst = con.prepareStatement("SELECT * FROM student WHERE email=? and Password=?");
//        pst.setString(1, username);
//        pst.setString(2, password);
//
//        rs = pst.executeQuery();
//
//        if (rs.next()) {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        } else {
//        loginResult.setText("Entered incorrect username or password");
//        usernameField.setText("");
//        passwordField.setText("");
//        }
//
//        } catch (SQLException | ClassNotFoundException e) {
//        throw new RuntimeException(e);
//        }
//        }

