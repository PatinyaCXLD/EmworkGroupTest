package com.test.app;

import com.test.data.manager.ApplicationDataSource;
import com.test.models.ApplicationList;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import com.test.models.FXRouter;


import java.io.IOException;



public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXRouter.bind(this, stage, "Emwork Intern Test", 500, 600);
        configRoute();
        FXRouter.goTo("main");
    }

    private static void configRoute(){
        String packageStr = "emwork/testintern/";
        FXRouter.when("appeal",
                packageStr+"appeal.fxml");
        FXRouter.when("main",
                packageStr+"main.fxml");
        FXRouter.when("admin",
                packageStr+"admin.fxml");
    }


    public static void main(String[] args) {
        launch();
    }
}
