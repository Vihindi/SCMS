package com.example.cw_draft5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class LoginController extends DatabaseConnection {
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Label validLogin;
    @FXML
    public Button loginButton;
    @FXML
    public Text loginResult;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;





    @FXML
    public void onClickCreateAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainRegisterPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void onClickLogin(ActionEvent event) throws IOException {
         String username = usernameField.getText();
         String password = passwordField.getText();

        if (username.equals("") && password.equals("")) {
            loginResult.setText("Please enter the username and password");

        } else {
            try {
                pst = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM student WHERE email=? and Password=?");
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();

                if (rs.next()) {
                    EventAttendanceController.username1 = username;
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    loginResult.setText("Entered incorrect username or password");
                    usernameField.setText("");
                    passwordField.setText("");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }

}
//package com.example.cw_draft5;
//        import java.sql.Connection;
//        import java.sql.DriverManager;
//
//public class DatabaseConnection {
//    static final String url = "jdbc:mysql://localhost:3306/scms";
//    static final String user = "root";
//    static final String password = "";
//
//
//    public static Connection getConnection() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Connecting to the database...");
//            return DriverManager.getConnection(url, user, password);
//        } catch (Exception e) {
//            throw new RuntimeException("Error connecting to the database", e);
//        }
//    }
//}