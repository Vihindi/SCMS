package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class AttendanceDatabase {

    private static final String DATABASE_URL = "jdbc:MySQL://localhost:3306/SACMS";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";


    public ObservableList<String> getClubNames() {
        ObservableList<String> clubNames = FXCollections.observableArrayList();
        ObservableList<String> clubIDs = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String query = "SELECT Name FROM club";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String clubName = resultSet.getString("Name");
                    clubNames.add(clubName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
        return clubNames;
    }

    public ObservableList<String> getEventNames(String selectedClub) {
        System.out.println(selectedClub);
        ObservableList<String> EventNames = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            String query = "SELECT e.eventName\n" +
                    "FROM eventscheduling e\n" +
                    "JOIN club c ON e.clubID = c.clubID\n" +
                    "WHERE c.Name = '" + selectedClub + "';\n";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String EventName = resultSet.getString("EventName");
                    EventNames.add(EventName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
        return EventNames;
    }

    public static void main(String[] args) {
        AttendanceDatabase AD = new AttendanceDatabase();
        System.out.println(AD.getClubNames());
    }

}
