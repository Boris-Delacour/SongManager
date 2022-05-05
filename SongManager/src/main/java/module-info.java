module com.application.songmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application to javafx.fxml;
    exports com.application;
}