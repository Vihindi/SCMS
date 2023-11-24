//package com.example.cw_draft5;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//
//public class DatabaseConnection {
//    static final String url = "jdbc:mysql://localhost/SACMS";
//    static final String user = "root";
//    static final String pass = "";
//    private static Connection connection;
//
//    public DatabaseConnection() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Connecting to the database...");
//            connection = DriverManager.getConnection(url, user, pass);
//            System.out.println("Connecting to the database successfully");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() {
//
//        return connection;
//    }
//
//}

package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
    static final String url = "jdbc:mysql://localhost:3306/SACMS";
    static final String user = "root";
    static final String password = "";


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to the database...");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public ObservableList<String> getClubNames() {
        ObservableList<String> clubNames = FXCollections.observableArrayList();
        ObservableList<String> clubIDs = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
}