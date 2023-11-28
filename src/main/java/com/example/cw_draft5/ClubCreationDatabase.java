package com.example.cw_draft5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public static boolean isClubIDExists(int clubID) {
        return isRecordExists("SELECT COUNT(*) FROM clubs WHERE ClubID = ?", clubID);
    }

    public static boolean isClubNameExists(String clubName) {
        return isRecordExists("SELECT COUNT(*) FROM clubs WHERE Name = ?", clubName);
    }


    private static boolean isRecordExists(String query, Object parameter) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (parameter instanceof Integer) {
                preparedStatement.setInt(1, (Integer) parameter);
            } else if (parameter instanceof String) {
                preparedStatement.setString(1, (String) parameter);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // If count is greater than 0, the record exists
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
        return false; // Return false in case of any error
    }

}