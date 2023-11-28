package com.example.cw_draft5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectClubController extends StudentRegisterController implements Initializable {

    @FXML
    private TableView<DisplayClubs> clubSelectionTable;
    @FXML
    private TableColumn<DisplayClubs, Integer> clubIDColumn;

    @FXML
    private TableColumn<DisplayClubs, String> clubDescriptColumn;

    @FXML
    private TableColumn<DisplayClubs, String> clubBenefitColumn;

    @FXML
    private TableColumn<DisplayClubs, String> clubNameColumn;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    private final ObservableList<DisplayClubs> clubData = FXCollections.observableArrayList();
    PreparedStatement pst;
    Connection conn = DatabaseConnection.getConnection();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeColumns();
        retrieveDataFromDatabase();
    }

    private void initializeColumns(){
        clubIDColumn.setCellValueFactory(cellData -> cellData.getValue().getClubIDProperty().asObject());
        clubNameColumn.setCellValueFactory(cellData -> cellData.getValue().getClubNameProperty());
        clubDescriptColumn.setCellValueFactory(cellData -> cellData.getValue().getClubDescriptionProperty());
        clubBenefitColumn.setCellValueFactory(cellData -> cellData.getValue().getClubBenefitsProperty());
    }

    private void retrieveDataFromDatabase(){
        try{
            pst = conn.prepareStatement("SELECT ClubID,Name, Description, Benefits FROM club");
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                DisplayClubs club = new DisplayClubs();
                club.getClubIDProperty().set(rs.getInt("ClubID"));
                club.getClubNameProperty().set(rs.getString("Name"));
                club.getClubDescriptionProperty().set(rs.getString("Description"));
                club.getClubBenefitsProperty().set(rs.getString("Benefits"));

                clubData.add(club);
            }
            clubSelectionTable.setItems(clubData);

            rs.close();
            pst.close();
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickJoinClub(ActionEvent event) {
        try {
            DisplayClubs selectedItem = clubSelectionTable.getSelectionModel().getSelectedItem();

            pst = DatabaseConnection.getConnection().prepareStatement("INSERT INTO student_club (StudentID, ClubID,Registered_date) VALUES (?,?,NOW())");
            pst.setString(1, student.getStudentID());
            pst.setInt(2, selectedItem.getClubIDProperty().get());
            pst.executeUpdate();

            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Successfully joined into a club");

            alert1.showAndWait().ifPresent(response1 -> {
                if (response1 == ButtonType.OK){
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setHeaderText("Do you want to select another club?");
                    alert2.getButtonTypes().setAll(ButtonType.YES,ButtonType.NO);

                    alert2.showAndWait().ifPresent(response2 -> {
                        if (response2 == ButtonType.YES) {
                            try {
                                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SelectClubPage.fxml")));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } else {
                            try {
                                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                    });

                }else {
                    try {
                        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SelectClubPage.fxml")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            });

        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please select a club");
            alert.showAndWait();
        }
    }
}


