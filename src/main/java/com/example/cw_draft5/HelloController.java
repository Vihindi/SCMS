package com.example.cw_draft5;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private JFXButton Menu;

    @FXML
    private JFXButton MenuBack;

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane ap;


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
            loadPage("home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void Logout(MouseEvent event) {

    }

    @FXML
    void clubAdmin(MouseEvent event) {

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

        System.out.println("Slider: " + slider);
        System.out.println("Slider Width: " + slider.getWidth());
    }

}
