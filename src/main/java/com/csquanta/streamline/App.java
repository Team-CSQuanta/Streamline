package com.csquanta.streamline;
import atlantafx.base.theme.Dracula;
import com.csquanta.streamline.Controllers.FXMLScene;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    public static Stage mainStage;
    public static Dracula dracula = new Dracula();
    private double x, y;
    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;
        FXMLScene scene = FXMLScene.load("/Fxml/Dashboard.fxml");
        Parent root =  scene.root;
        Scene startingAnimation = new Scene(root);
        startingAnimation.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.setScene(startingAnimation);
        primaryStage.setScene(startingAnimation);
        primaryStage.setResizable(false);
        Application.setUserAgentStylesheet(dracula.getUserAgentStylesheet());
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }


}

