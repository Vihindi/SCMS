package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.util.Pair;


public class DatabaseConnection {
    static final String url = "jdbc:mysql://localhost:3306/sacms";
    static final String user = "root";
    static final String password = "";
    public Connection connection;
    public DatabaseConnection() {
        // Initialize the connection when the DatabaseConnection object is created
        connection = getConnection();
    }


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to the database...");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }


    public ObservableList<String> getEventNames(String selectedClub) {
        return common("SELECT e.eventName\n" +
                "FROM eventscheduling e \n" +
                "JOIN club c ON e.clubID = c.clubID\n" +
                "WHERE c.Name = '" + selectedClub + "';", "EventName");
    }

    public Boolean getEventCode(String eventCode, String selectedEvent) {
        ObservableList<String> EventName = common("SELECT EventName \n" +
                "FROM eventscheduling  \n" +
                "WHERE  EventCode = '" + eventCode + "';", "EventName");
        String eventName = EventName.get(0);
        System.out.println(EventName);
        ObservableList<String> eventCodeList = common("SELECT EventCode " +
                "FROM eventscheduling " +
                "WHERE EventName = '" + selectedEvent + "';", "EventCode");

        System.out.println(eventCodeList);
        String eventCode2 = eventCodeList.get(0);


        if (!eventName.isEmpty() || !eventCode2.isEmpty()) {
            return Objects.equals(eventCode, eventCode2);
        }
        return false;

    }

    public ObservableList<String> getClubNames() {
        return common("SELECT Name FROM club", "Name");
    }

    public ObservableList<String> common(String query, String ColumnName) {
        ObservableList<String> List = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String column = resultSet.getString(ColumnName);
                    List.add(column);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database errors
        }
        return List;

    }

    public String ClubId(String selectedClub) {
        ObservableList<String> ClubID = common("SELECT clubID FROM club WHERE Name = '" + selectedClub + "';", "clubID");
        return ClubID.get(0);
    }

    public String EventId(String selectedEvent) {
        ObservableList<String> EventID = common("SELECT EventID FROM eventscheduling WHERE EventName = '" + selectedEvent + "';", "EventID");
        return EventID.get(0);
    }


    public Pair<ObservableList<String>, ObservableList<String>> getAttendance(String selectedClub, String selectedEvent) {
        String ClubId = ClubId(selectedClub);
        String EventId = EventId(selectedEvent);
        ObservableList<String> studentIDQuery = common("SELECT StudentID FROM attendance WHERE clubID = '" + ClubId + "' AND EventID = '" + EventId + "';", "StudentID");
        ObservableList<String> studentNameList = FXCollections.observableArrayList();
        for (String studentID : studentIDQuery) {
            ObservableList<String> studentNameQuery = common("SELECT FullName FROM student WHERE StudentID = '" + studentID + "';", "FullName");

            // Check if the studentNameQuery result is not empty before adding to the list
            if (!studentNameQuery.isEmpty()) {
                String studentName = studentNameQuery.get(0);
                studentNameList.add(studentName);
            }
        }

        System.out.println(ClubId + " " + EventId);
        System.out.println(studentIDQuery);
        System.out.println("Student Names: " + studentNameList);

        // Return a Pair containing both studentIDQuery and studentNameList
        return new Pair<>(studentIDQuery, studentNameList);

    }

    public void MarkAttendance(String username, String club, String event) {
        String ClubId = ClubId(club);
        String EventId = EventId(event);
        System.out.println(username);

        // Assuming you have a method named `common` that returns ObservableList<String>
        ObservableList<String> studentID = common("SELECT StudentID FROM student WHERE Email = '" + username + "';", "StudentID");

        // Check if the studentID list is not empty before proceeding
        if (!studentID.isEmpty()) {
            String studentId = studentID.get(0);

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement stmt = conn.createStatement()) {

                System.out.println("Inserting records into the table...");

                // Use PreparedStatement to prevent SQL injection
                String sql = "INSERT INTO attendance (ClubID, EventID, StudentID) VALUES (?, ?, ?)";

                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, ClubId);
                    preparedStatement.setString(2, EventId);
                    preparedStatement.setString(3, studentId);

                    preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Student not found for username: " + username);
        }
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



