package com.csquanta.streamline;

import atlantafx.base.theme.Dracula;
import com.csquanta.streamline.Controllers.*;
import com.csquanta.streamline.Models.*;
import com.csquanta.streamline.Networking.ReadThreadClient;
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

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class App extends Application {
    public  static StackPane root;
    public static Stage mainStage;
    public static Dracula dracula = new Dracula();
    static TaskManager taskManager = new TaskManager();

    private double x, y;
    public App() {
    }

    @Override
    public void init(){
        UserInformation.deserializeUserInfo();
        Shop.deserializeShop();
        Task.deserializeTasks();
        TaskIdGenerator.deserializeTaskID();
        taskManager.startTaskChecking();


        System.out.println("The challenge mode is now: " + UserInformation.userInfo.getChallengeMode());
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
    @Override
    public void stop() {
        UserInformation.serializeUserInfo();
        Shop.serializeShop();
        Task.serializeTasks();
        TaskIdGenerator.serializeTaskID();
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void newLoad() throws IOException {
        HBox header, exitOption;
        root.getChildren().removeAll(root.getChildren());
        exitOption = FXMLLoader.load(requireNonNull(App.class.getResource("/Fxml/ExitOption.fxml")));
        header = FXMLLoader.load(requireNonNull(App.class.getResource("/Fxml/Header.fxml")));
        StackPane.setAlignment(header, Pos.TOP_CENTER);
        root.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(KeyCode.ALT == event.getCode()){
                MainStageController.modalPaneForExit.setAlignment(Pos.BOTTOM_CENTER);
                MainStageController.modalPaneForExit.usePredefinedTransitionFactories(Side.BOTTOM);
                MainStageController.modalPaneForExit.show(exitOption);
               taskManager.stopTaskChecking();
            }
        });
        root.getChildren().addAll(header, MainStageController.modalPaneForExit, HeaderController.modalPaneForHeader, SidebarController.modalPaneForSignOut, CreateANewTaskController.modalPaneForTaskCreator, PomodoroPageController.modalPaneForPomodoroPage, ReadThreadClient.modalPaneForChallengeRequest);
    }
}

