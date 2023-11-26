package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.List;

public class AttendanceTrackController {

    @FXML
    private TableView<Student> AttendanceTable;

    @FXML
    private TableColumn<Student, String> NameColumn;

    @FXML
    private TableColumn<Student, Integer> studentIDColumn;

    @FXML
    private AnchorPane ap;

    @FXML
    private ComboBox<String> clubComboBox;

    @FXML
    private ComboBox<String> eventComboBox;

    @FXML
    private Text textId;

    private DatabaseConnection databaseConnection;

    public ObservableList<Student> students;

    @FXML
    void initialize() {
        databaseConnection = new DatabaseConnection();
        populateClubComboBox();
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        try {
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void populateClubComboBox() {
        clubComboBox.setItems(databaseConnection.getClubNames());
    }




    @FXML
     void ClickClubSearch(ActionEvent event) {
        String selectedClub = SelectedClub();
        eventComboBox.setItems(databaseConnection.getEventNames(selectedClub));


    }

    @FXML
    void ClickEventSearch(ActionEvent event) {
        String selectedClub = SelectedClub();
        String selectedEvent = SelectedEvent();
        Pair<ObservableList<String>, ObservableList<String>> result = databaseConnection.getAttendance(selectedClub, selectedEvent);
        ObservableList<String> studentIDQueryResult = result.getKey();
        ObservableList<String> studentNameListResult = result.getValue();
        System.out.println(studentIDQueryResult);
        System.out.println(studentNameListResult);
        // Create a list of Student objects using the retrieved data
        students = FXCollections.observableArrayList();

        for (int i = 0; i < studentIDQueryResult.size(); i++) {
            String studentID = studentIDQueryResult.get(i);
            String studentName = studentNameListResult.get(i);
            System.out.println(studentID);
            System.out.println(studentName);

            Student student = new Student(studentID,studentName);
            students.add(student);
        }

        // Set the items in the TableView
        AttendanceTable.setItems(students);

    }



    public String SelectedClub(){
        return clubComboBox.getValue();
    }

    public String SelectedEvent(){
        return eventComboBox.getValue();
    }



}
