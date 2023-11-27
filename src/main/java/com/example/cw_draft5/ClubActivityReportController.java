package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.w3c.dom.events.MouseEvent;

import java.time.LocalDate;
import java.util.List;

public class ClubActivityReportController {

    @FXML
    private TableView<Event> ClubActivityReportTable;

    @FXML
    private TableColumn<Event, String> DescriptionColumn;

    @FXML
    private TableColumn<Event, String> EventCodeColumn;

    @FXML
    private TableColumn<Event, LocalDate> EventDateColumn;

    @FXML
    private TableColumn<Event, Integer> EventIDColumn;

    @FXML
    private TableColumn<Event, String> EventNameColumn;

    @FXML
    private TableColumn<Event, String> ModeColumn;

    @FXML
    private TableColumn<Event, String> TImeSlotColumn;

    @FXML
    private TableColumn<Event, String> VenueColumn;

    @FXML
    private ComboBox<String> clubComboBox;

    @FXML
    private ImageView SearchBtn;

    private AttendanceDatabase attendanceDatabase;

    private DatabaseConnection databaseConnection;

    @FXML
    void initialize() {
        databaseConnection = new DatabaseConnection();
        populateClubComboBox();
        initializeTableColumns();
    }

    private void initializeTableColumns() {
        EventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        EventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        EventDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        EventCodeColumn.setCellValueFactory(new PropertyValueFactory<>("eventCode"));
        ModeColumn.setCellValueFactory(new PropertyValueFactory<>("mode"));
        VenueColumn.setCellValueFactory(new PropertyValueFactory<>("venue"));
        TImeSlotColumn.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    private void populateClubComboBox() {
        List<String> clubNames = databaseConnection.getClubNames();
        ObservableList<String> clubNamesObservable = FXCollections.observableArrayList(clubNames);
        clubComboBox.setItems(clubNamesObservable);
    }

    private String selectedClub() {
        return clubComboBox.getValue();
    }

    private void fetchAndDisplayEvents(int clubID) {
        // Fetch events from the database based on the clubID
        // Use the clubID to filter events relevant to the selected club

        // Example: Fetch events using a method in DatabaseConnection
        ObservableList<Event> events = databaseConnection.getEventsForClub(clubID);

        // Update the TableView with the new list of events
        ClubActivityReportTable.setItems(events);
    }


    public String SelectedClub() {
        return clubComboBox.getValue();
    }

    private void updateTableView(List<Event> events) {
        ObservableList<Event> eventObservableList = FXCollections.observableArrayList(events);
        ClubActivityReportTable.setItems(eventObservableList);
    }

    public void SearchBtn(javafx.scene.input.MouseEvent mouseEvent) {
        String selectedClub = selectedClub();
        int clubID = databaseConnection.getClubID(selectedClub);

        // Fetch and display events for the selected club
        fetchAndDisplayEvents(clubID);
    }
}