package com.test.controllers;

import com.test.data.services.Filterer;
import com.test.models.Application;
import com.test.models.ApplicationList;
import com.test.models.FXRouter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class adminController {
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private TextArea reasonTextArea;
    @FXML
    private TextField statusTextField;
    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
    @FXML
    private ListView<Application> applicationListView;


    @FXML
    public void initialize(){
        ApplicationList toShow = mainController.applicationList.filterBy(new Filterer<Application>() {
            @Override
            public boolean filter(Application application) {
                return (application.getStatus().equals("รอพิจารณา"));
            }
        });
        applicationListView.getItems().addAll(toShow.getAllApplications());
        handleSelectedListView();
        clearSelectedMember();
    }

    private void handleSelectedListView() {
        applicationListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Application>() {
                    @Override
                    public void changed(ObservableValue<? extends Application> observable, Application oldValue, Application newValue) {
                        System.out.println(newValue + " is selected");
                        if(newValue != null) showSelectedMember(newValue);
                    }
                });
    }

    private void showSelectedMember(Application application) {
        fullNameTextField.setText(application.getFullName());
        positionTextField.setText(application.getPosition());
        emailTextField.setText(application.getEmail());
        phoneTextField.setText(application.getPhone());
        typeTextField.setText(application.getType());
        statusTextField.setText(application.getStatus());
        reasonTextArea.setText(application.getReason());
        fromTextField.setText(application.getFromDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
        toTextField.setText(application.getToDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")));

    }
    private  void clearSelectedMember() {
        fullNameTextField.setText("");
        positionTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");
        typeTextField.setText("");
        statusTextField.setText("");
        reasonTextArea.setText("");
    }

    public void approveButton(){
        mainController.applicationList.find(fullNameTextField.getText()).setStatus("อนุมัติ");
        mainController.applicationDataSource.writeData(mainController.applicationList);
    }
    public void declineButton(){
        mainController.applicationList.find(fullNameTextField.getText()).setStatus("ไม่อนุมัติ");
        mainController.applicationDataSource.writeData(mainController.applicationList);
    }
    public void backButton(){
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
