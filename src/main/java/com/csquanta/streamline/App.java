package com.csquanta.streamline;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Objects;

import javafx.stage.Stage;

public class App extends Application {
    public static Stage mainStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/Dashboard.fxml")));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root);
        // setting global stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/global.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }


}

