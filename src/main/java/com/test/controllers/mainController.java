package com.test.controllers;

import com.test.data.manager.ApplicationDataSource;
import com.test.models.ApplicationList;
import com.test.models.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class mainController {
    @FXML
    private Button submitButton;
    @FXML
    private PasswordField adminPasswordField;

    static ApplicationDataSource applicationDataSource = new ApplicationDataSource("src/data/applications","applications.csv");
    static ApplicationList applicationList = new ApplicationList();

    ArrayList data = new ArrayList<>();

    @FXML
    public void initialize(){
        applicationList = applicationDataSource.readData();
        adminPasswordField.setDisable(true);
        adminPasswordField.setOpacity(0);
        submitButton.setOpacity(0);
        submitButton.setDisable(true);
    }

    public void appealButton(){
        try {
            FXRouter.goTo("appeal");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void submitButtonHandle(){
        if (adminPasswordField.getText().equals("EmWorkAdmin")) {
            try {
                FXRouter.goTo("admin");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void adminButton(){
        adminPasswordField.setDisable(false);
        adminPasswordField.setOpacity(1);
        submitButton.setOpacity(1);
        submitButton.setDisable(false);
    }
}
