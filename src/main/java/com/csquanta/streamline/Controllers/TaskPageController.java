package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

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
        for (Task t: Task.taskObject.getTasksList()){
            try {
                TaskScene = FXMLScene.load("/Fxml/TaskBlock.fxml");
                TaskBlockController taskBlockController = (TaskBlockController) TaskScene.controller;
                taskBlockController.getLabelContainer().getChildren().removeAll();
                Label tagLabel = new Label(t.getTag());

                tagLabel.setMinWidth(Region.USE_PREF_SIZE + 30);
                tagLabel.setMaxHeight(24);
                tagLabel.setMinHeight(24);
                tagLabel.setPrefHeight(24);
                tagLabel.setPadding(new Insets(0, 5, 0, 5));
                tagLabel.setStyle("-fx-border-color:  #9580FF;-fx-border-radius: 5px;-fx-font-size: 12px;");
                tagLabel.setTextAlignment(TextAlignment.CENTER);
                tagLabel.setAlignment(Pos.CENTER);
                Label priorityTag = new Label(t.getPriority());
                priorityTag.setMinWidth(Region.USE_PREF_SIZE + 30);
                priorityTag.setMaxHeight(24);
                priorityTag.setMinHeight(24);
                priorityTag.setPrefHeight(24);
                priorityTag.setPadding(new Insets(0, 5, 0, 5));
                priorityTag.setStyle("-fx-border-color:  #9580FF;-fx-border-radius: 5px;-fx-font-size: 12px;");
                priorityTag.setTextAlignment(TextAlignment.CENTER);
                priorityTag.setAlignment(Pos.CENTER);
                taskBlockController.getLabelContainer().getChildren().addAll(tagLabel, priorityTag);
                taskBlockController.setTaskTitle(t.getTaskTitle());
                taskBlockController.setDueDate(t.getFormattedDueDate());
                taskBlockController.setNumOfPomodoroSession(String.valueOf(t.getNumOfSessions()));
                taskBlockController.setBreakContainer(taskBlockController.getTimerContainer());
                taskBlockController.setTempTimerContainer(taskBlockController.getTimerContainer());
                System.out.println("On Task Page Controller" + taskBlockController.getTempTimerContainer());
                taskBlockController.setMaxLoopsCounts(t.getNumOfSessions());

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
