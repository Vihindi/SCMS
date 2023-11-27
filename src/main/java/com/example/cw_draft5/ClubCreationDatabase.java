package com.example.cw_draft5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClubCreationDatabase {
    public static void AddClubToDB(Club club) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO club (ClubID, Name, Description, Mission, Created_date, clubAdvisorID, Benefits) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, club.getClubID());
                statement.setString(2, club.getClubName());
                statement.setString(3, club.getClubDescription());
                statement.setString(4, club.getClubMission());
                statement.setDate(5, java.sql.Date.valueOf(club.getStartDate()));
                statement.setInt(6, club.getClubAdvisorID());
                statement.setString(7, club.getClubBenefits());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}