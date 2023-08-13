package com.csquanta.streamline.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

public class TaskBlockController {

    @FXML
    private Label dueDate;

    @FXML
    private HBox labelContainer;

    @FXML
    private Label numOfPomodoroSession;

    @FXML
    private Label taskTitle;

    @FXML
    private VBox timerContainer;


    public Label getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.setText(dueDate);
    }

    public Label getNumOfPomodoroSession() {
        return numOfPomodoroSession;
    }

    public void setNumOfPomodoroSession(String sessions) {
        this.numOfPomodoroSession.setText(sessions);
    }

    public Label getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle.setText(taskTitle);
    }


    @FXML
    void Start(ActionEvent event) throws IOException {

        try {
            FXMLScene fxmlScene = FXMLScene.load("/Fxml/timer.fxml");
            VBox timer = (VBox) fxmlScene.root;

            timerContainer.getChildren().setAll(timer);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
