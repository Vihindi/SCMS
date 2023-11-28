package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.*;
import java.util.Objects;


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