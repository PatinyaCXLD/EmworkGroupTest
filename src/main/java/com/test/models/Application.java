package com.test.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private String type;
    private String reason;
    private String status;
    private LocalDateTime appealTime;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Application(String fullName,String position,String email,String phone,String type,String reason){
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppealTime(LocalDateTime appealTime) {
        this.appealTime = appealTime;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getAppealTime() {
        return appealTime;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        String toReturn = getFullName() + '/' + getAppealTime() + '/' + getStatus();
        return toReturn;
    }


}
