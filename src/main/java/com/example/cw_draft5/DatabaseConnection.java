
package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;

public class DatabaseConnection {
    static final String url = "jdbc:mysql://localhost:3306/SACMS";
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

