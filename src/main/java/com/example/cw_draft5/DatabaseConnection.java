package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javafx.scene.paint.Color;
import javafx.util.Pair;


public class DatabaseConnection {
    static final String url = "jdbc:mysql://localhost:3306/sacms";
    static final String user = "root";
    static final String password = "";
    public Connection connection;
    public DatabaseConnection() {
        // Initialize the connection when the DatabaseConnection object is created
        connection = getConnection();
    }


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



