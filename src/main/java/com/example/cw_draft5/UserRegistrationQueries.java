package com.example.cw_draft5;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRegistrationQueries extends DatabaseConnection{
    public List<String> getClubNameForReport() {
        List<String> clubNames = new ArrayList<>();
        String query = "SELECT Name FROM club";  // Assuming the column name is "Name"

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                clubNames.add(resultSet.getString("Name"));  // Use the correct column name
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubNames;
    }



    public List<Student> getStudentsByClub(String clubName) {
        List<Student> students = new ArrayList<>();
        String query = "SELECT s.* FROM student s " +
                "JOIN student_club sc ON s.studentID = sc.studentID " +
                "JOIN club c ON sc.clubID = c.clubID " +
                "WHERE c.Name = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, clubName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Create Student objects and add them to the list
                    Student student = new Student(
                            resultSet.getString("studentID"),
                            resultSet.getString("FullName"),
                            resultSet.getDate("DOB").toLocalDate(),
                            resultSet.getString("Contact"),
                            resultSet.getString("Gender"),
                            resultSet.getString("Email"),
                            resultSet.getString("Location"),
                            resultSet.getString("Grade"),
                            resultSet.getString("GuardianName"),
                            resultSet.getInt("GuardianContact"),
                            resultSet.getString("Skills"),
                            resultSet.getString("Password"));
                    students.add(student);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

}
