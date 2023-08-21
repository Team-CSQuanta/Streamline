package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.SlideOutUp;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.Models.MyTimer;
import com.csquanta.streamline.Models.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PomodoroPageController implements Initializable {
    private Task task;
    private static int sessionCounter = 0;
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public static ModalPane modalPaneForPomodoroPage = new ModalPane();

    @FXML
    private Button button;

    @FXML
    private ImageView closeBtnImg;

    @FXML
    private Label minutesLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label secondsLabel;

    @FXML
    private Label sessionInfoLabel;
    @FXML
    private HBox labelContainer;

    public HBox getLabelContainer() {
        return labelContainer;
    }

    public void setLabelContainer(HBox labelContainer) {
        this.labelContainer = labelContainer;
    }

    @FXML
    private Label taskTitle;

    public String getTaskTitle() {
        return taskTitle.getText();
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle.setText(taskTitle);
    }

    @FXML
    void buttonClicked(ActionEvent event){
        if(button.getText().equals("Start session")){
            button.setText("Running");
            sessionInfoLabel.setText("Session " + (sessionCounter + 1));
            MyTimer timer = new MyTimer(5, minutesLabel, secondsLabel, button, "Session", sessionCounter, task.getNumOfSessions());
            timer.t.setDaemon(true);
            timer.t.start();
            sessionCounter++;
        }
        else if(button.getText().equals("Take Break") && sessionCounter != task.getNumOfSessions()){
            button.setText("Running");
            sessionInfoLabel.setText("Running break");
            MyTimer timer = new MyTimer(2, minutesLabel, secondsLabel, button, "Break", sessionCounter, task.getNumOfSessions());
            timer.t.setDaemon(true);
            timer.t.start();
        }
        else if(button.getText().equals("Finish")){
            button.setVisible(false);
            new FadeOut(button).play();
            sessionCounter = 0;
            closeBtnImg.setVisible(true);
            new FadeIn(closeBtnImg).play();
        }
    }

    @FXML
    void closeBtnClicked(MouseEvent event) throws IOException {
        TaskViewController.deleteATask(task);
        TaskViewController.reload();
        PomodoroPageController.modalPaneForPomodoroPage.hide(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeBtnImg.setVisible(false);

    }
}


