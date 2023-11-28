package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AttendenceReportController {

    @FXML
    private TableView<?> AttendanceTable;

    @FXML
    private TableColumn<?, ?> NameColumn;

    @FXML
    private ComboBox<?> clubComboBox;

    @FXML
    private ComboBox<?> eventComboBox;

    @FXML
    private TableColumn<?, ?> studentIDColumn;

    @FXML
    void ClickClubSearch(ActionEvent event) {

    }

    @FXML
    void ClickEventSearch(ActionEvent event) {

    }

}
