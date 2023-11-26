package com.example.cw_draft5;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

public class StudentRegisterController extends DisplayClubs{
    @FXML
    public TextField studentID;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    public TextField guardianNo;
    @FXML
    private TextField userLocation;
    @FXML
    private TextField guardianName;

    @FXML
    private TextField contactNo;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField fullName;

    @FXML
    private TextField genderType;

    @FXML
    private TextField grade;

    @FXML
    private PasswordField setPassword;

    @FXML
    private TextArea skills;
    PreparedStatement pst;
    static Student student;

    private void displayErrorMessage(String error){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(error);
        alert.show();

    }

    @FXML
    public void onClickStudentRegister(ActionEvent event) throws IOException {
        if (!validateStudentID()){
            displayErrorMessage("Please enter the correct student number");
            return;
        }
        try {
            student = new Student(studentID.getText(),fullName.getText(),dateOfBirth.getValue(),contactNo.getPrefColumnCount(),genderType.getText(),emailAddress.getText(),userLocation.getText(),grade.getPrefColumnCount(),guardianName.getText(),guardianNo.getPrefColumnCount(),skills.getText(),setPassword.getText());

            pst = DatabaseConnection.getConnection().prepareStatement("INSERT INTO student (StudentID, FullName, DOB, Contact, Gender, Email, Location, Grade, GuardianName, GuardianContact, Skills, Password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, student.getStudentID());
            pst.setString(2, student.getFullName());
            pst.setString(3, student.getDateOfBirth());
            pst.setInt(4, student.getContactNo());
            pst.setString(5, student.getGender());
            pst.setString(6, student.getEmail());
            pst.setString(7, student.getLocation());
            pst.setInt(8, student.getGrade());
            pst.setString(9, student.getGuardianName());
            pst.setInt(10, student.getGuardianContactNo());
            pst.setString(11, student.getSkills());
            pst.setString(12, student.getPassword());
            int affectedRows = pst.executeUpdate();


            if (affectedRows > 0) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SelectClubPage.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enter all the information");
                alert.showAndWait();
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Another student exist with same student number");
            alert.showAndWait();

        }catch (MysqlDataTruncation e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter the correct student number");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onClickExistAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickCreateAccount(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainRegisterPage.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean validateStudentID() {
        if (studentID.getText().length()!=8) {
            studentID.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
        return true;
    }
    private void validateName(KeyEvent event){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        String character = event.getCharacter();
        if (!character.matches("[a-zA-Z\\s]")&&fullName.getText().isEmpty()){
            event.consume();
            alert.setHeaderText("Please enter the correct name");
            alert.show();
        }
    }
}


//if (username.equals("") && password.equals("")) {
//        loginResult.setText("Please enter the username and password");
//
//        } else {
//        try {
//        Class.forName("com.mysql.jdbc.Driver");
//        con = DriverManager.getConnection("jdbc:mysql://localhost/SACMS", "root", "");
//        pst = con.prepareStatement("SELECT * FROM student WHERE email=? and Password=?");
//        pst.setString(1, username);
//        pst.setString(2, password);
//
//        rs = pst.executeQuery();
//
//        if (rs.next()) {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        } else {
//        loginResult.setText("Entered incorrect username or password");
//        usernameField.setText("");
//        passwordField.setText("");
//        }
//
//        } catch (SQLException | ClassNotFoundException e) {
//        throw new RuntimeException(e);
//        }
//        }



//    package com.example.cw_draft5;
//
//        import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
//        import javafx.event.ActionEvent;
//        import javafx.fxml.FXML;
//        import javafx.fxml.FXMLLoader;
//        import javafx.fxml.Initializable;
//        import javafx.scene.Node;
//        import javafx.scene.Parent;
//        import javafx.scene.Scene;
//        import javafx.scene.control.*;
//        import javafx.scene.input.KeyEvent;
//        import javafx.stage.Stage;
//
//        import java.io.IOException;
//        import java.net.URL;
//        import java.sql.PreparedStatement;
//        import java.sql.SQLException;
//        import java.sql.SQLIntegrityConstraintViolationException;
//        import java.util.Objects;
//        import java.util.ResourceBundle;
//
//public class StudentRegisterController implements Initializable {
//    public TextField studentID;
//    @FXML
//    private Stage stage;
//    @FXML
//    private Scene scene;
//    @FXML
//    private Parent root;
//    public TextField classTeacherNo;
//    public TextField guardianNo;
//    @FXML
//    private TextField userLocation;
//    @FXML
//    private TextField guardianName;
//
//    @FXML
//    private TextField classTeacherName;
//
//    @FXML
//    private TextField contactNo;
//
//    @FXML
//    private DatePicker dateOfBirth;
//
//    @FXML
//    private TextField emailAddress;
//
//    @FXML
//    private TextField fullName;
//
//    @FXML
//    private TextField genderType;
//
//    @FXML
//    private TextField grade;
//
//    @FXML
//    private PasswordField setPassword;
//
//    @FXML
//    private TextArea skills;
//    PreparedStatement pst;
//
//
//    public TextField getClassTeacherNo() {
//        return classTeacherNo;
//    }
//
//    public void setClassTeacherNo(TextField classTeacherNo) {
//        this.classTeacherNo = classTeacherNo;
//    }
//
//    public TextField getGuardianNo() {
//        return guardianNo;
//    }
//
//    public void setGuardianNo(TextField guardianNo) {
//        this.guardianNo = guardianNo;
//    }
//
//    public TextField getUserLocation() {
//        return userLocation;
//    }
//
//    public void setUserLocation(TextField userLocation) {
//        this.userLocation = userLocation;
//    }
//
//    public TextField getGuardianName() {
//        return guardianName;
//    }
//
//    public void setGuardianName(TextField guardianName) {
//        this.guardianName = guardianName;
//    }
//
//    public TextField getClassTeacherName() {
//        return classTeacherName;
//    }
//
//    public void setClassTeacherName(TextField classTeacherName) {
//        this.classTeacherName = classTeacherName;
//    }
//
//    public TextField getContactNo() {
//        return contactNo;
//    }
//
//    public void setContactNo(TextField contactNo) {
//        this.contactNo = contactNo;
//    }
//
//    public DatePicker getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(DatePicker dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public TextField getEmailAddress() {
//        return emailAddress;
//    }
//
//    public void setEmailAddress(TextField emailAddress) {
//        this.emailAddress = emailAddress;
//    }
//
//    public TextField getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(TextField fullName) {
//        this.fullName = fullName;
//    }
//
//    public TextField getGenderType() {
//        return genderType;
//    }
//
//    public void setGenderType(TextField genderType) {
//        this.genderType = genderType;
//    }
//
//    public TextField getGrade() {
//        return grade;
//    }
//
//    public void setGrade(TextField grade) {
//        this.grade = grade;
//    }
//
//    public PasswordField getSetPassword() {
//        return setPassword;
//    }
//
//    public void setSetPassword(PasswordField setPassword) {
//        this.setPassword = setPassword;
//    }
//
//    public TextArea getSkills() {
//        return skills;
//    }
//
//    public void setSkills(TextArea skills) {
//        this.skills = skills;
//    }
//
//    private void displayErrorMessage(String error){
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(error);
//        alert.show();
//
//    }
//
//
//    @FXML
//    void onClickStudentRegister(ActionEvent event) throws IOException {
//        try {
//            if (studentID.getText().isEmpty()) {
//                displayErrorMessage("Please enter the student number");
//                return;
//            } else if (fullName.getText().isEmpty()) {
//                displayErrorMessage("Please enter the name");
//                return;
//            }
//
//
//            pst = DatabaseConnection.getConnection().prepareStatement("INSERT INTO student (StudentID, FullName, DOB, Contact, Gender, Email, Location, Grade, GuardianName, GuardianContact, Skills, Password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
//            pst.setString(1, studentID.getText());
//            pst.setString(2, fullName.getText());
//            pst.setString(3, String.valueOf(dateOfBirth.getValue()));
//            pst.setString(4, contactNo.getText());
//            pst.setString(5, genderType.getText());
//            pst.setString(6, emailAddress.getText());
//            pst.setString(7, userLocation.getText());
//            pst.setString(8, grade.getText());
//            pst.setString(9, guardianName.getText());
//            pst.setString(10, guardianNo.getText());
//            pst.setString(11, skills.getText());
//            pst.setString(12, setPassword.getText());
//
//            int affectedRows = pst.executeUpdate();
//
//            if (affectedRows > 0) {
//                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
//                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Error");
//                alert.setHeaderText("Enter all the information");
//                alert.showAndWait();
//            }
//        }catch (SQLIntegrityConstraintViolationException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Another student exist with same student number");
//            alert.showAndWait();
//
//        }catch (MysqlDataTruncation e){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Please enter the correct student number");
//            alert.showAndWait();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void onClickExistAccount(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public void onClickCreateAccount(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainRegisterPage.fxml")));
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        studentID.addEventFilter(KeyEvent.KEY_TYPED,this::validateStudentID);
//        fullName.addEventFilter(KeyEvent.KEY_TYPED,this::validateName);
//
//    }
//    private void validateStudentID(KeyEvent event) {
//        String character = event.getCharacter();
//        if (!character.matches("\\d") && studentID.getText().isEmpty() && studentID.getText().length()!=8) {
//            event.consume();
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Please enter a valid ID");
//            alert.show();
//        }
//    }
//    private void validateName(KeyEvent event){
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        String character = event.getCharacter();
//        if (!character.matches("[a-zA-Z\\s]")&&fullName.getText().isEmpty()){
//            event.consume();
//            alert.setHeaderText("Please enter the correct name");
//            alert.show();
//        }
//    }
//
//
//}



//    public void initialize1() {
//        initializeColumns();
//        retrieveDataFromDatabase();
//    }
//
//    private void initializeColumns(){
//        clubNameColumn.setCellValueFactory(cellData -> cellData.getValue().getClubNameProperty());
//        clubDescriptColumn.setCellValueFactory(cellData -> cellData.getValue().getClubDescriptionProperty());
//        clubBenefitColumn.setCellValueFactory(cellData -> cellData.getValue().getClubBenefitsProperty());
//
//
//    }

//    private void start(Stage primaryStage){
//        try{
//            TableView<ObservableList<String>> tableView = new TableView<>();
//            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
//
//            Connection conn = DatabaseConnection.getConnection();
//            PreparedStatement pst = conn.prepareStatement("SELECT Name, Description, Benefits FROM club");
//            ResultSet rs = pst.executeQuery();
//
//            for(int i=0; i<3;i++){
//                final int j=i;
//                TableColumn<ObservableList<String>>,String> column
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


