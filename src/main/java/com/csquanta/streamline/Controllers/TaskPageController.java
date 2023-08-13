package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskPageController implements Initializable {
    public static int row;
    public static  int column;
    @FXML
    public GridPane taskGridPane;
    @FXML
    private VBox taskMainContainer;

//    public GridPane getTaskGridPane() {
//        return taskGridPane;
//    }

    @FXML
    private ScrollPane taskScrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         row = 1;
         column = 0;
        FXMLScene createATaskScene;
        try{
            createATaskScene = FXMLScene.load("/Fxml/CreateANewTask.fxml");
            taskGridPane.add(createATaskScene.root,column++, row);
        }catch (IOException e){
            System.out.println("File not found");
        }
        FXMLScene TaskScene;
        try{
            TaskScene = FXMLScene.load("/Fxml/TaskBlock.fxml");
            taskGridPane.add(TaskScene.root,column++, row);
        }catch (IOException e){
            System.out.println("File not found");
        }


    }
}
