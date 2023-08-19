package com.csquanta.streamline.Controllers;

import animatefx.animation.Pulse;
import animatefx.animation.Wobble;
import com.csquanta.streamline.Models.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskBlockController implements Initializable {
    private Task task;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
// MyTimer


    @FXML
    private Label clockLabel;

    @FXML
    private ProgressBar clockProgressBar;

    @FXML
    private VBox container;
    public VBox getContainer() {
        return container;
    }

    public void setContainer(VBox container) {
        this.container = container;
    }
    @FXML
    private Label sessionCount;
    @FXML
    private Button toggleBtn;


 @FXML
    private ImageView startImage;

    public HBox getLabelContainer() {
        return labelContainer;
    }

    public void setLabelContainer(HBox labelContainer) {
        this.labelContainer = labelContainer;
    }

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
    private ImageView clockImage;
    @FXML
    void mouseEntered(MouseEvent event) {
        Wobble wobble = new Wobble(this.clockImage);
        wobble.play();
    }

    @FXML
    void showTaskDescription(MouseEvent event) throws IOException {
        FXMLScene taskDescription = FXMLScene.load("/Fxml/TaskView.fxml");
        TaskViewController taskViewController = (TaskViewController) taskDescription.controller;
        CreateANewTaskController.modalPaneForTaskCreator.show(taskDescription.root);

        taskViewController.setTask(task);
        taskViewController.setTitle(task.getTaskTitle());
        taskViewController.setDescription(task.getDescription());
        taskViewController.setDueDate(task.getDueDate());
        taskViewController.setPriority(task.getPriority());
        taskViewController.setTag(task.getTag());
        taskViewController.setPomodoroSessions(String.valueOf(task.getNumOfSessions()));
        new Pulse(taskDescription.root).play();
    }
    @FXML
    void showPomodoroTimer(MouseEvent event) throws IOException {
        FXMLScene pomodoroPage = FXMLScene.load("/Fxml/PomodoroPage.fxml");
        CreateANewTaskController.modalPaneForTaskCreator.show(pomodoroPage.root);
        PomodoroPageController controller = (PomodoroPageController) pomodoroPage.controller;
        controller.setTask(task);
        new Pulse(pomodoroPage.root).play();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


