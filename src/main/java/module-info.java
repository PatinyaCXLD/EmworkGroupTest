module com.test{

    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.swing;

    opens com.test.models to javafx.fxml;
    exports com.test.models;

    exports com.test.controllers;
    opens com.test.controllers to javafx.fxml;

    opens com.test.app to javafx.fxml;
    exports com.test.app;
}