package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StudentRegisterController {
    @FXML
    private TextField userLocation;
    @FXML
    private TextField GuardianName;

    @FXML
    private TextField classTeacherName;

    @FXML
    private TextField contactNo;

    @FXML
    private DatePicker dataOfBirth;

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

    @FXML
    void onClickStudentRegister(ActionEvent event) {
        AddUserDataIntoDatabase addUserDataIntoDatabase = new AddUserDataIntoDatabase(fullName.getText(),dataOfBirth.getValue(),contactNo.getText(),genderType.getText(),emailAddress.getText(),userLocation.getText(),grade.getText(),classTeacherName.getText(),GuardianName.getText(),skills.getText(),setPassword.getText());
        addUserDataIntoDatabase.insert();

    }

}
