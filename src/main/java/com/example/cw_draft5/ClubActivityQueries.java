package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ClubActivityQueries extends DatabaseConnection{
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