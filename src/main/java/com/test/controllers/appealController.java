package com.test.controllers;

import com.test.app.MainApplication;
import com.test.data.manager.ApplicationDataSource;
import com.test.models.Application;
import com.test.models.ApplicationList;
import com.test.models.FXRouter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class appealController {
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextArea reasonTextArea;
    @FXML
    private Label errorLabel;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private Label phoneErrorLabel;
    @FXML
    private Label reasonErrorLabel;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;
    @FXML
    private Label timeErrorLabel;

    @FXML
    public void initialize(){
        String[] types = {"ลาป่วย", "ลากิจ", "พักร้อน", "อื่น ๆ"};
        typeComboBox.setItems(FXCollections.observableArrayList(types));

        nameErrorLabel.setText("*");
        phoneErrorLabel.setText("*");
        reasonErrorLabel.setText("*");
        errorLabel.setText("");
        timeErrorLabel.setText("*");

    }
    @FXML
    protected void submitButtonHandle() {
        System.out.println(fromDatePicker.getValue());
        System.out.println(toDatePicker.getValue());
       if (checkNotNull() && checkTime()) {
           Application application = new Application(fullNameTextField.getText(),positionTextField.getText(), emailTextField.getText(), phoneTextField.getText(),typeComboBox.getValue(),reasonTextArea.getText());
           application.setAppealTime(LocalDateTime.now());
           application.setStatus("รอพิจารณา");
           application.setFromDate(fromDatePicker.getValue());
           application.setToDate(toDatePicker.getValue());
           mainController.applicationList.addApplication(application);
           mainController.applicationDataSource.writeData(mainController.applicationList);
       }
    }

    private Boolean checkNotNull(){
        Boolean notNull = true;
        if (fullNameTextField.getText().equals("")) {
            nameErrorLabel.setText("ต้องกรอกชื่อ-นามสกุล");
            notNull = false;
        }
        if (phoneTextField.getText().equals("")) {
            phoneErrorLabel.setText("ต้องกรอกเบอร์โทรศัพท์");
            notNull = false;
        }
        if (reasonTextArea.getText().equals("")) {
            reasonErrorLabel.setText("ต้องกรอกข้อมูลสาเหตุการลา");
            notNull = false;
        }
        if (fromDatePicker.getValue() == null){
            timeErrorLabel.setText("ต้องกรอกข้อมูลเวลาให้ถูกต้อง");
        }
        return notNull;
    }

    private Boolean checkTime(){
        if (toDatePicker.getValue().isBefore(fromDatePicker.getValue()) || fromDatePicker.getValue().isBefore(LocalDate.now())) {
            timeErrorLabel.setText("ต้องกรอกข้อมูลเวลาให้ถูกต้อง");
            return false;
        }
        if (typeComboBox.getValue().equals("พักร้อน")){
            Period periodRest = Period.between(fromDatePicker.getValue(),toDatePicker.getValue());
            Period periodBefore = Period.between(LocalDate.now(), fromDatePicker.getValue());
            if (periodBefore.getDays() < 3) {
                timeErrorLabel.setText("ต้องแจ้งลาพักร้อนล่วงหน้าอย่างน้อย 3 วัน");
                return false;
            }
            if (periodRest.getDays() > 2) {
                timeErrorLabel.setText("ไม่สามารถลาพักร้อนได้เกิน 2 วันติดต่อกัน");
                return false;
            }
        }
        return true;
    }

    public void backButton(){
        try {
            FXRouter.goTo("main");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}