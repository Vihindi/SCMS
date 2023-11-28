module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.base;
    requires javafx.graphics;

    opens com.example.cw_draft5 to javafx.fxml;
    exports com.example.cw_draft5;

}