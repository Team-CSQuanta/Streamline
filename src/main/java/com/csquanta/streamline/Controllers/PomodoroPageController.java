package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.MyTimer;
import com.csquanta.streamline.Models.Task;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class PomodoroPageController implements Initializable {
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @FXML
    private Label minutesLabel;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label secondsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void pomodoroSessionRunner(Task t){
        for(int i = 1; i <= t.getNumOfSessions(); i++){
            MyTimer timer = new MyTimer(2, 60, minutesLabel, secondsLabel);
            timer.t.setDaemon(true);
            timer.t.start();

        }
    }

}


