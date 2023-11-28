package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;

public class StudentReportController {

    @FXML
    private ComboBox<String> ClubDropDown;

    @FXML
    private TableColumn<Student, String> Contact;

    @FXML
    private TableColumn<Student, LocalDate> DOB;

    @FXML
    private TableColumn<Student, String> Email;

    @FXML
    private TableColumn<Student, String> FullName;

    @FXML
    private TableColumn<Student, String> Gender;

    @FXML
    private TableColumn<Student, Integer> Grade;

    @FXML
    private TableColumn<Student, String> GuardianName;

    @FXML
    private TableColumn<Student, Integer> GuardianContact;

    @FXML
    private TableColumn<Student, String> Location;


    @FXML
    private ImageView SearchingBtn;

    @FXML
    private TableColumn<Student, String> Skills;

    @FXML
    private TableColumn<Student, String> StudentID;

    @FXML
    private TableView<Student> StudentTable;

    private UserRegistrationQueries userRegistrationQueries;

    @FXML
    public void initialize() {
        // Initialize the database connection
        userRegistrationQueries = new UserRegistrationQueries();

        // Populate the ClubDropDown with club names
        List<String> clubNames = userRegistrationQueries.getClubNameForReport();
        ClubDropDown.setItems(FXCollections.observableArrayList(clubNames));

        // Initialize the table columns
        Contact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        DOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        FullName.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        Grade.setCellValueFactory(new PropertyValueFactory<>("Grade"));
        GuardianName.setCellValueFactory(new PropertyValueFactory<>("GuardianName"));
        GuardianContact.setCellValueFactory(new PropertyValueFactory<>("GuardianContactNo"));
        Location.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Skills.setCellValueFactory(new PropertyValueFactory<>("Skills"));
        StudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));


    }

    @FXML
    public void SearchBtn(MouseEvent mouseEvent) {
        // Get selected club name
        String selectedClub = ClubDropDown.getValue();

        // Retrieve students for the selected club from the database
        List<Student> students = userRegistrationQueries.getStudentsByClub(selectedClub);

        // Update the TableView with the retrieved student data
        StudentTable.setItems(FXCollections.observableArrayList(students));
    }
}
