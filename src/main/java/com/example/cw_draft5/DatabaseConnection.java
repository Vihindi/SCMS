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
import java.time.LocalDate;

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

        try (Connection connection = getConnection()) {
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
    public ObservableList<Event> getEventsForClub(int clubID) {
        ObservableList<Event> events = FXCollections.observableArrayList();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM eventscheduling WHERE clubID = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, clubID);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int eventId = resultSet.getInt("eventID");
                        String eventName = resultSet.getString("eventName");
                        LocalDate eventDate = resultSet.getDate("eventDate").toLocalDate();
                        String eventCode = resultSet.getString("eventCode");
                        String mode = resultSet.getString("mode");
                        String venue = resultSet.getString("venue");
                        String timeSlot = resultSet.getString("timeSlot");
                        String description = resultSet.getString("description");

                        Event event = new Event(clubID, eventId, eventName, eventDate, eventCode, mode, venue, timeSlot, description);
                        events.add(event);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
        return events;
    }


    public ObservableList<String> getEventNames(String selectedClub) {
        ObservableList<String> eventNames = FXCollections.observableArrayList();

        try (Connection connection = getConnection()) {
            String query = "SELECT e.eventName " +
                    "FROM eventscheduling e " +
                    "JOIN club c ON e.clubID = c.clubID " +
                    "WHERE c.Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, selectedClub);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String eventName = resultSet.getString("eventName");
                        eventNames.add(eventName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
        return eventNames;
    }

    public void addEvent(Event event, String selectedClub) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO eventscheduling (clubID, EventName, EventDate, EventCode, Mode, Venue, TimeSlot, Description) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                int clubID = getClubID(selectedClub);
                statement.setInt(1, clubID);
                statement.setString(2, event.getEventName());
                statement.setDate(3, java.sql.Date.valueOf(event.getDate()));
                statement.setString(4, event.getEventCode());
                statement.setString(5, event.getMode());
                statement.setString(6, event.getVenue());
                statement.setString(7, event.getTimeSlot());
                statement.setString(8, event.getDescription());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
    }

    int getClubID(String clubName) {
        try (Connection connection = getConnection()) {
            String query = "SELECT clubID FROM club WHERE Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, clubName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("clubID");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
        return -1; // Return -1 if club ID is not found (handle this case appropriately in your application)
    }
}
