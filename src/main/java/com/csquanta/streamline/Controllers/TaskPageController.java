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
    private static int  row = 1;
    private static int column = 0;
    @FXML
    private GridPane taskGridPane;

    @FXML
    private VBox taskMainContainer;

    @FXML
    private ScrollPane taskScrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
