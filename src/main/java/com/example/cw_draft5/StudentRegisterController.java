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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class StudentRegisterController extends DisplayClubs {
    @FXML
    public TextField studentID;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    public TextField guardianNo;
    @FXML
    private TextField userLocation;
    @FXML
    private TextField guardianName;

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
    PreparedStatement pst;
    static Student student;


    private void displayErrorMessage(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.show();

    }

    @FXML
    public void onClickStudentRegister(ActionEvent event) throws IOException {
        try {
            student = new Student(studentID.getText(), fullName.getText(), dateOfBirth.getValue(), contactNo.getText(), genderType.getText(), emailAddress.getText(), userLocation.getText(), grade.getText(), guardianName.getText(), guardianNo.getText(), skills.getText(), setPassword.getText());
            if (!validateStudentID() || !validateName() || !validateDataOfBirth() || !validateContactNo() || !validateGender() || !validateEmail() || !validateGrade() || !validateGuardian() || !validateGuardianContactNo() || !validateStudentPassword()) {
                return;
            }
            LocalDate dateOfBirth = student.getDateOfBirth();
            java.sql.Date sqlDateOfBirth = java.sql.Date.valueOf(dateOfBirth);

            pst = DatabaseConnection.getConnection().prepareStatement("INSERT INTO student (StudentID, FullName, DOB, Contact, Gender, Email, Location, Grade, GuardianName, GuardianContact, Skills, Password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, student.getStudentID());
            pst.setString(2, student.getFullName());
            pst.setDate(3, sqlDateOfBirth);
            pst.setString(4, student.getContactNo());
            pst.setString(5, student.getGender());
            pst.setString(6, student.getEmail());
            pst.setString(7, student.getLocation());
            pst.setString(8, student.getGrade());
            pst.setString(9, student.getGuardianName());
            pst.setString(10, student.getGuardianContactNo());
            pst.setString(11, student.getSkills());
            pst.setString(12, student.getPassword());
            int affectedRows = pst.executeUpdate();


            if (affectedRows > 0) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SelectClubPage.fxml")));
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
        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Another student exist with same student number");
            alert.showAndWait();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickExistAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickCreateAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainRegisterPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public boolean validateStudentID() {
        if (student.getStudentID().isEmpty()) {
            displayErrorMessage("Please enter the student number");
            studentID.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        } else if (student.getStudentID().length()!=8) {
            displayErrorMessage("Please enter a valid student number");
            return false;
        }
        return true;
    }

    private boolean validateName() {
        if (student.getFullName().isEmpty()) {
            displayErrorMessage("Please enter the correct name");
            fullName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
        for (char a: student.getFullName().toCharArray())
            if (Character.isDigit(a)){
                displayErrorMessage("Please do not enter any numbers");
                fullName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                return false;
            }
        return true;
    }


    private boolean validateDataOfBirth() {
        if (student.getDateOfBirth() == null || student.getDateOfBirth().isAfter(LocalDate.now())) {
            displayErrorMessage("Please enter a valid date");
            return false;
        }
        return true;
    }

    private boolean validateContactNo() {
        String contactNumber = contactNo.getText();
        if (!contactNumber.matches("\\d{10}")) {
            displayErrorMessage("Please enter a valid 10-digit contact number");
            contactNo.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
        return true;
    }
    private boolean validateGender(){
        String genderText = genderType.getText().toLowerCase();

        if (!"female".equals(genderText) && !"male".equals(genderText)) {
            displayErrorMessage("Please enter either 'female' or 'male' for gender");
            genderType.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }

        return true;
    }

    private boolean validateEmail(){
        String email = emailAddress.getText();

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            displayErrorMessage("Please enter a valid email address");
            emailAddress.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }

        return true;
    }

    private boolean validateGrade(){
        try{
            int grade = Integer.parseInt(student.getGrade());
            if(grade < 0 || grade > 13) {
                displayErrorMessage("Enter the correct grade");
                return false;
            }
        }catch (NumberFormatException e){
            displayErrorMessage("Please enter numbers only");
            return false;
        }
        return true;
    }

    private boolean validateGuardian(){
        if (student.getGuardianName().isEmpty()) {
            displayErrorMessage("Please enter the correct guardian name");
            fullName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
        for (char a: student.getGuardianName().toCharArray())
            if (Character.isDigit(a)){
                displayErrorMessage("Please do not enter any numbers");
                fullName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                return false;
            }
        return true;
    }

    private boolean validateGuardianContactNo() {
        String guardianContactNumber = guardianNo.getText();
        if (!guardianContactNumber.matches("\\d{10}")) {
            displayErrorMessage("Please enter a valid 10-digit contact number");
            guardianNo.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
        return true;
    }

    private boolean validateStudentPassword(){
        if(student.getPassword().isEmpty()){
            displayErrorMessage("Please set a strong password for your club profile");
            return false;
        }
        return true;
    }
}
