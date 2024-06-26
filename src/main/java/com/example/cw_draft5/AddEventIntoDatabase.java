package com.example.cw_draft5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEventIntoDatabase {
    public static void addToDatabase(Event event, String selectedClub) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Obtain clubID based on the selected club name
            int clubID = getClubID(selectedClub);

            String sql = "INSERT INTO eventscheduling (ClubID, EventName, EventDate, EventCode, Mode, Venue, TimeSlot, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, clubID);
                statement.setString(2, event.getEventName());
                statement.setDate(3, java.sql.Date.valueOf(event.getDate()));
                statement.setString(4, event.getEventCode());
                statement.setString(5, event.getMode());
                statement.setString(6, event.getVenue());
                statement.setString(7, event.getTimeSlot());
                statement.setString(8, event.getDescription());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Event added to the database successfully.");
                } else {
                    System.out.println("Failed to add event to the database.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error executing SQL statement", e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }


    private static int getClubID(String clubName) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT ClubID FROM club WHERE Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, clubName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("ClubID");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1; // Return -1 if club ID is not found (handle this case appropriately in your application)
    }
}


