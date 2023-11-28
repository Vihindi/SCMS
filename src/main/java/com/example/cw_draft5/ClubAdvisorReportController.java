package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClubAdvisorReportController {

    @FXML
    private TableColumn<ClubAdvisor, Integer> ClubAdvisorID;

    @FXML
    private TableView<ClubAdvisor> ClubAdvisorReportTable;

    @FXML
    private TableColumn<ClubAdvisor, String> ContactNo;

    @FXML
    private TableColumn<ClubAdvisor, String> Email;

    @FXML
    private TableColumn<ClubAdvisor, String> FullName;

    @FXML
    private TableColumn<ClubAdvisor, String> Gender;

    @FXML
    private TableColumn<ClubAdvisor, String> Password;

    @FXML
    private void initialize() {
        ObservableList<ClubAdvisor> AdvisorData = GetAdvisorFromDatabase();
        initializeTableColumns();
        ClubAdvisorReportTable.setItems(AdvisorData);

    }


    private void initializeTableColumns() {
        ClubAdvisorID.setCellValueFactory(new PropertyValueFactory<>("ClubAdvisorID"));
        FullName.setCellValueFactory(new PropertyValueFactory<>("clubAdvisorName"));
        Email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        ContactNo.setCellValueFactory(new PropertyValueFactory<>("ContactNo"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
    }
    private ObservableList<ClubAdvisor> GetAdvisorFromDatabase() {
        ObservableList<ClubAdvisor> AdvisorData = FXCollections.observableArrayList();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM clubadvisor";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    ClubAdvisor clubAdvisor = new ClubAdvisor(
                            resultSet.getInt("clubAdvisorID"),
                            resultSet.getString("FullName"),
                            resultSet.getString("Email"),
                            resultSet.getString("ContactNo"),
                            resultSet.getString("Gender"),
                            resultSet.getString("Password"));

                    AdvisorData.add(clubAdvisor);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return AdvisorData;
    }

}
