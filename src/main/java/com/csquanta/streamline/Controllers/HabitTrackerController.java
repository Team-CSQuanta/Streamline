package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.App;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class HabitTrackerController {
    @FXML
    void onOverviewClicked(MouseEvent event) throws IOException {
        FXMLScene scene = FXMLScene.load("/Fxml/Dashboard.fxml");
        Parent root =  scene.root;
        Scene dashboard = new Scene(root);
        Stage primaryStage = App.mainStage;
        primaryStage.setScene(dashboard);
    }

    @FXML
    void onTasksBtnClicked(MouseEvent event) throws IOException{
        FXMLScene scene = FXMLScene.load("/Fxml/TodoList.fxml");
        Parent root =  scene.root;
        Scene toDoList = new Scene(root);
        Stage primaryStage = App.mainStage;
        primaryStage.setScene(toDoList);
    }
}
