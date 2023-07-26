package com.csquanta.streamline;

import atlantafx.base.controls.ModalPane;
import atlantafx.base.theme.Dracula;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static java.util.Objects.requireNonNull;

public class App extends Application {
    public static Stage mainStage;
    private final ModalPane modalPaneForExit = new ModalPane();
    public static Dracula dracula = new Dracula();
    private double x, y;
    public App() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(requireNonNull(getClass().getResource("/Fxml/MainStage.fxml")));
        StackPane root =  fxmlLoader.load();
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
        HBox exitOption = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ExitOption.fxml")));
        HBox header = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/Header.fxml")));
        root.getChildren().addAll(modalPaneForExit, header);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(KeyCode.ESCAPE == event.getCode()){
                modalPaneForExit.setAlignment(Pos.BOTTOM_CENTER);
                modalPaneForExit.usePredefinedTransitionFactories(Side.BOTTOM);
                modalPaneForExit.show(exitOption);
            }
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

