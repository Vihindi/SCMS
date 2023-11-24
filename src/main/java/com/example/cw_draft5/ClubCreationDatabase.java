package com.example.cw_draft5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClubCreationDatabase {
    public static void AddClubToDB(Club club) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO club (ClubID, Name, Description, Mission, Created_date, clubAdvisorID, Benefits) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Club.getClubID());
                statement.setString(2, Club.getClubName());
                statement.setString(3, Club.getClubDescription());
                statement.setString(4, Club.getClubMission());
                statement.setDate(5, java.sql.Date.valueOf(Club.getStartDate()));
                statement.setInt(6, Club.getClubAdvisorID());
                statement.setString(7, Club.getClubBenefits());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}