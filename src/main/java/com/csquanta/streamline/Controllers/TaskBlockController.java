package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.Wobble;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;

public class TaskBlockController implements Initializable {

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
//    private int pomodoroSession;
//
//    public int getPomodoroSession() {
//        return pomodoroSession;
//    }
//
//    public void setPomodoroSession(int pomodoroSession) {
//        this.pomodoroSession = pomodoroSession;
//    }

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
    void mouseEnteredinStarBox(MouseEvent event) {
        Wobble wobble = new Wobble(this.startImage);
        wobble.play();
    }

    @FXML
    void start(MouseEvent event) {
        Label sessionNumber;
        if (event.getSource() instanceof VBox button) {
            Parent parent = button.getParent();
            sessionNumber = (Label) parent.lookup("#numOfPomodoroSession");
            System.out.println("Printing session number " +sessionNumber.getText());
            try {
                FXMLScene fxmlScene = FXMLScene.load("/Fxml/timer.fxml");
                TimerController timerController = (TimerController) fxmlScene.controller;
                VBox timer = (VBox) fxmlScene.root;
                timerController.setPomodoroSessions(Integer.parseInt(sessionNumber.getText()));
                System.out.println(timerController.getPomodoroSessions() + "   Printing the value of timer controller");
                timerContainer.getChildren().setAll(timer);

                new FadeIn(timer).play();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
