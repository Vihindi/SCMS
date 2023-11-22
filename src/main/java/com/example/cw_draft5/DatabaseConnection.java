package com.example.cw_draft5;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    static final String url = "jdbc:mysql://localhost/SACMS";
    static final String user = "root";
    static final String pass = "";
    private Connection connection;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to the database...");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connecting to the database successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
