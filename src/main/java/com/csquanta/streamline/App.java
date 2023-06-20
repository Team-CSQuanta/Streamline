package com.csquanta.streamline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.nio.file.Paths;
import java.util.Objects;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/loginPage.fxml"));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root);
        // setting global stylesheet
        scene.getStylesheets().add(Paths.get("src/main/resources/Styles/global.css").toUri().toURL().toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }


}

