package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
        if(taskGridPane.getChildren().size()>0){
            taskGridPane.getChildren().removeAll();
        }
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
        for (Task t: Task.taskObject.getTasksList()){
            try {
                TaskScene = FXMLScene.load("/Fxml/TaskBlock.fxml");
                TaskBlockController taskBlockController = (TaskBlockController) TaskScene.controller;
                taskBlockController.setTaskTitle(t.getTaskTitle());
                taskBlockController.setDueDate(t.getFormattedDueDate());
                taskBlockController.setNumOfPomodoroSession(String.valueOf(t.getNumOfSessions()));
                taskGridPane.add(TaskScene.root, column++, row);

                if(column == 3){
                    column = 0;
                    row++;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }




}
