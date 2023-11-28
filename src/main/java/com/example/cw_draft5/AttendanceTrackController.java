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
    private Label venueLabel;

    @FXML
    private Text textId;

    @FXML
    private Label EventDateLabel;

    @FXML
    private Label ModeLabel;

    @FXML
    private Label TimeSlotLabel;

    @FXML
    private Label sumOfAttendance;

    private AttendanceQuaries attendanceQuaries;

    private ObservableList<String> RegisteredNameList;


    public ObservableList<Student> students;

    private int sumAttendance;




    @FXML
    void initialize() {
        attendanceQuaries = new AttendanceQuaries();
        populateClubComboBox();
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        // Use setCellValueFactory to set the value factory for the NameColumn
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        // Customize the appearance of the cells in the NameColumn
        NameColumn.setCellFactory(column -> {
            return new TableCell<Student, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    // Check if the cell is empty
                    if (item == null || empty) {
                        setText(null);
                        setStyle(""); // Clear the background color
                    } else {
                        setText(item);

                        // Check if the current item is in RegisteredNameList
                        if (RegisteredNameList.contains(item)) {
                            // Set the background color to red for registered students
                            setStyle("-fx-background-color: #f08080;");
                        } else {
                            // Set the background color to green for other students
                            setStyle("-fx-background-color: lightgreen;");


                        }
                    }
                }
            };
        });

    }



    private void populateClubComboBox() {
        clubComboBox.setItems(attendanceQuaries.getClubNames());
    }




    @FXML
     void ClickClubSearch(ActionEvent event) {
        String selectedClub = SelectedClub();
        eventComboBox.setItems(attendanceQuaries.getEventNames(selectedClub));


    }

    @FXML
    void ClickEventSearch(ActionEvent event) {
        RegisteredNameList = ViewAttendance();
        Report();

    }

    public ObservableList<String>ViewAttendance(){
        String selectedClub = SelectedClub();
        String selectedEvent = SelectedEvent();
        Pair<ObservableList<String>, ObservableList<String>> PresentStudents = attendanceQuaries.getAttendance(selectedClub, selectedEvent);
        Pair<ObservableList<String>, ObservableList<String>>  RegisteredStudents= attendanceQuaries.getAbsetees(selectedClub, selectedEvent);

        ObservableList<String> studentIDQueryResult = PresentStudents.getKey();
        ObservableList<String> studentNameListResult = PresentStudents.getValue();
        ObservableList<String> RegisteredstudentID = RegisteredStudents.getKey();
        ObservableList<String> RegisteredNameList= RegisteredStudents.getValue();

        System.out.println("Before remove "+RegisteredstudentID);
        RegisteredstudentID.removeAll(studentIDQueryResult);
        RegisteredNameList.removeAll(studentNameListResult);

        System.out.println("After remove "+RegisteredstudentID);
        // Create a list of Student objects using the retrieved data
        students = FXCollections.observableArrayList();

        for (int i = 0; i < studentIDQueryResult.size(); i++) {
            String studentID = studentIDQueryResult.get(i);
            String studentName = studentNameListResult.get(i);
            System.out.println(studentID);
            System.out.println(studentName);
            sumAttendance++;

            Student student = new Student(studentID,studentName);
            students.add(student);
        }
        for (int i = 0; i < RegisteredstudentID.size(); i++) {
            String studentID = RegisteredstudentID.get(i);
            String studentName = RegisteredNameList.get(i);
            System.out.println(studentID);
            System.out.println(studentName);



            Student student = new Student(studentID,studentName);
            students.add(student);
        }
        sumOfAttendance.setText(String.valueOf(sumAttendance));



        // Set the items in the TableView
        AttendanceTable.setItems(students);
        return RegisteredNameList;
    }



    public String SelectedClub(){
        return clubComboBox.getValue();
    }

    public String SelectedEvent(){
        return eventComboBox.getValue();
    }


    public void Report(){
        String club = SelectedClub();
        String event = SelectedEvent();
        ObservableList<String> eventDetails = attendanceQuaries.AttendanceRecords(club,event);
        EventDateLabel.setText(eventDetails.get(0));
        ModeLabel.setText(eventDetails.get(1));
        venueLabel.setText(eventDetails.get(2));
        TimeSlotLabel.setText(eventDetails.get(3));



    }



}
