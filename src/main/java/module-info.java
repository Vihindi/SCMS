module com.example.cw_draft5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.example.cw_draft5 to javafx.fxml;
    exports com.example.cw_draft5;
}