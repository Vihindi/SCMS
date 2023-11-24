package com.example.cw_draft5;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarController {

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, Integer> clubID;

    @FXML
    private TableColumn<Event, LocalDate> date;

    @FXML
    private TableColumn<Event, String> description;

    @FXML
    private TableColumn<Event, String> eventCode;

    @FXML
    private TableColumn<Event, Integer> eventID;

    @FXML
    private TableColumn<Event, String> eventName;

    @FXML
    private TableColumn<Event, String> mode;

    @FXML
    private TableColumn<Event, String> timeSlot;

    @FXML
    private TableColumn<Event, String> venue;


}
