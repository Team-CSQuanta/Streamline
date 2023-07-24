package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.partial.Expand;
import com.csquanta.streamline.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TodoListController {
    @FXML
    void onOverviewClicked(MouseEvent event) throws IOException {
        FXMLScene scene = FXMLScene.load("/Fxml/Dashboard.fxml");
        Parent root =  scene.root;
        Scene dashboard = new Scene(root);
        Stage primaryStage = App.mainStage;
        primaryStage.setScene(dashboard);
    }
    @FXML
    void onHabitTrackerBtnClicked(MouseEvent event) throws IOException{
        FXMLScene scene = FXMLScene.load("/Fxml/HabitTracker.fxml");
        Parent root =  scene.root;

        Scene habitTracker = new Scene(root);
        Stage primaryStage = App.mainStage;
        primaryStage.setScene(habitTracker);
    }
}
