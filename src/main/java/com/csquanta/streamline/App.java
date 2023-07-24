package com.csquanta.streamline;
import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import com.csquanta.streamline.Controllers.FXMLScene;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class App extends Application {
    public static Stage mainStage;
    public static PrimerDark primerDark = new PrimerDark();
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        FXMLScene scene = FXMLScene.load("/Fxml/Dashboard.fxml");
        Parent root =  scene.root;
        Scene startingAnimation = new Scene(root);
        primaryStage.setScene(startingAnimation);
        primaryStage.setScene(startingAnimation);
        primaryStage.setResizable(false);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }


}

