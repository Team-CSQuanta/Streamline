package com.csquanta.streamline;
import com.csquanta.streamline.Controllers.FXMLScene;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    public static Stage mainStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        FXMLScene scene = FXMLScene.load("/Fxml/StartingAnimation.fxml");
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

