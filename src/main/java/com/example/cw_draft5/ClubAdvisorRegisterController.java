package com.example.cw_draft5;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

public class ClubAdvisorRegisterController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    public TextField clubAdvisorName;
    @FXML
    public TextField clubAdvisorContact;
    @FXML
    public TextField clubAdvisorGender;
    @FXML
    public TextField clubAdvisorEmail;
    @FXML
    public PasswordField clubAdvisorPassword;


}
