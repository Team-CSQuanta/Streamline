package com.csquanta.streamline;
import atlantafx.base.controls.ModalPane;
import atlantafx.base.theme.Dracula;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static java.util.Objects.requireNonNull;

public class App extends Application {
    public  static StackPane root;
    public static Stage mainStage;
    public static Dracula dracula = new Dracula();
    private double x, y;
    public App() {
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(requireNonNull(getClass().getResource("/Fxml/StartingAnimation.fxml")));
        root =  fxmlLoader.load();
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
        primaryStage.setResizable(false);
        Application.setUserAgentStylesheet(dracula.getUserAgentStylesheet());
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
    public static void addNodesToMainStack(Node node, Pos position){
        StackPane.setAlignment(node, position);
        root.getChildren().add(node);
    }
    public static void addModalPaneToMainStack(ModalPane modalPane){
        root.getChildren().add(modalPane);
    }
    public static void removeNode(Node node){
        root.getChildren().remove(node);
    }
    public static void removeModalPane(ModalPane modalPane){
        root.getChildren().remove(modalPane);
    }

}

