package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

    public void setDueDate(Label dueDate) {
        this.dueDate = dueDate;
    }

    public Label getNumOfPomodoroSession() {
        return numOfPomodoroSession;
    }

    public void setNumOfPomodoroSession(Label numOfPomodoroSession) {
        this.numOfPomodoroSession = numOfPomodoroSession;
    }

    public Label getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(Label taskTitle) {
        this.taskTitle = taskTitle;
    }
}
