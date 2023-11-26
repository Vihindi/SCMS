package com.example.cw_draft5;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private JFXButton Menu;

    @FXML
    private JFXButton MenuBack;

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;

    @FXML
    private ImageView Calendar;

    @FXML
    private ImageView Report;

    @FXML
    private ImageView clubList;

    @FXML
    private ImageView eventAttend;


    @FXML
    private VBox slider;

    private boolean isMenuVisible = false; // Track the visibility state of the menu

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize the 'slider' to its initial position, translating it to the left by 176 units
        slider.setTranslateX(-176);

        // Set event handlers for mouse clicks on the 'Menu' button and 'MenuBack' button
        Menu.setOnMouseClicked(event -> {
            if (!isMenuVisible) {
                // If the menu is not visible, show the menu
                showMenu();
            } else {
                // If the menu is visible, hide the menu
                hideMenu();
            }
        });

        MenuBack.setOnMouseClicked(event -> {
            if (!isMenuVisible) {
                // If the menu is not visible, show the menu
                showMenu();
            } else {
                // If the menu is visible, hide the menu
                hideMenu();
            }
        });
    }


    public void showMenu() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);

        slide.setToX(0);
        slide.play();

        slide.setOnFinished((ActionEvent e) -> {
            Menu.setVisible(false);
            MenuBack.setVisible(true);
        });

        isMenuVisible = true;
    }

    public void hideMenu() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.4));
        slide.setNode(slider);

        slide.setToX(-slider.getWidth());
        slide.play();

        slide.setOnFinished((ActionEvent e) -> {
            Menu.setVisible(true);
            MenuBack.setVisible(false);
        });

        isMenuVisible = false;


    }


    @FXML
    void AttendanceAdmin(MouseEvent event) {
        try {
            loadPage("AttendanceTrack");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void EventAdmin(MouseEvent event) {
        try {
            loadPage("EventScheduling");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void Logout(MouseEvent event) {

    }

    @FXML
    void clubAdmin(MouseEvent event) {
        try {
            loadPage("Clubcreation");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPage(String page) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(page + ".fxml"));
            Parent root = loader.load();
            bp.setCenter(root);
        } catch (NullPointerException e) {
            System.err.println("FXML file '" + page + ".fxml' not found");
            throw e;
        }

    }

    @FXML
    void CalendarClick(MouseEvent event) {
        try {
            loadPage("Calendar");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void EventAttendClick(MouseEvent event) {
        try {
            loadPage("EventAttendance");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void ReportClick(MouseEvent event) {
        try {
            loadPage("ClubActivityReport");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void clubListClick(MouseEvent event) {
        try {
            loadPage("View clubs");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
