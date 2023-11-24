package com.example.cw_draft5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEventIntoDatabase {
    public static void addToDatabase(Event event) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO eventscheduling (ClubID, EventID, EventName, EventDate, EventCode, Mode, Venue, TimeSlot, Description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, event.getClubID());
                statement.setInt(2, event.getEventID());
                statement.setString(3, event.getEventName());
                statement.setDate(4, java.sql.Date.valueOf(event.getDate()));
                statement.setString(5, event.getEventCode());
                statement.setString(6, event.getMode());
                statement.setString(7, event.getVenue());
                statement.setString(8, event.getTimeSlot());
                statement.setString(9, event.getDescription());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
