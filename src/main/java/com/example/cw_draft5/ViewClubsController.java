package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ViewClubsController {

    @FXML
    private Button CustomBtn;

    @FXML
    private TableColumn<Club,Integer> clubAdvisorID;

    @FXML
    private TableColumn<Club, String> clubBenefits;

    @FXML
    private TableColumn<Club, String> clubDescription;

    @FXML
    private TableColumn<Club, Integer> clubID;

    @FXML
    private TableColumn<Club, String> clubMission;

    @FXML
    private TableColumn<Club, String> clubName;

    @FXML
    private TableColumn<Club, LocalDate> startDate;

    @FXML
    private TableView<Club> viewTable;

    @FXML
    private void initialize() {
        ObservableList<Club> clubData = GetClubsFromDatabase();
        initializeTableColumns();
        viewTable.setItems(clubData);
        //populateTable();
    }

    //private void populateTable() {
    //ObservableList<Club> clubData = GetClubsFromDatabase();
    //viewTable.setItems(clubData);
    // }

    private void initializeTableColumns() {
        clubID.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubDescription.setCellValueFactory(new PropertyValueFactory<>("clubDescription"));
        clubMission.setCellValueFactory(new PropertyValueFactory<>("clubMission"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        clubAdvisorID.setCellValueFactory(new PropertyValueFactory<>("clubAdvisorID"));
        clubBenefits.setCellValueFactory(new PropertyValueFactory<>("clubBenefits"));
    }
    private ObservableList<Club> GetClubsFromDatabase() {
        ObservableList<Club> clubData = FXCollections.observableArrayList();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM club";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Club club = new Club(
                            resultSet.getInt("ClubID"),
                            resultSet.getString("Name"),
                            resultSet.getString("Description"),
                            resultSet.getString("Mission"),
                            resultSet.getDate("Created_Date").toLocalDate(),
                            resultSet.getInt("clubAdvisorID"),
                            resultSet.getString("Benefits")
                    );

                    clubData.add(club);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubData;
    }


}