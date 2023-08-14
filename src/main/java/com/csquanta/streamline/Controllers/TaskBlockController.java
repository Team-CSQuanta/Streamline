package com.csquanta.streamline.Controllers;

import animatefx.animation.Wobble;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        try {
            FXMLScene fxmlScene = FXMLScene.load("/Fxml/timer.fxml");
            VBox timer = (VBox) fxmlScene.root;

            timerContainer.getChildren().setAll(timer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
