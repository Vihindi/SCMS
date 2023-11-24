package com.example.cw_draft5;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    static final String url = "jdbc:mysql://localhost:3306/scams";
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
}