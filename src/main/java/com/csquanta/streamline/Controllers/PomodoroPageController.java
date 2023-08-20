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
    private Label taskTitle;
    @FXML
    void buttonClicked(ActionEvent event) throws InterruptedException {
        if(button.getText().equals("Start session")){
            button.setText("Running");
            sessionInfoLabel.setText("Session " + (sessionCounter + 1));
            System.out.println("Session counter before: " + sessionCounter);
            MyTimer timer = new MyTimer(1, minutesLabel, secondsLabel, button, "Session", sessionCounter, task.getNumOfSessions());
            timer.t.setDaemon(true);
            timer.t.start();
            sessionCounter++;
            System.out.println("Session counter after: " + sessionCounter);
        }
        else if(button.getText().equals("Take Break") && sessionCounter != task.getNumOfSessions()){
            System.out.println(" In take break block");
            button.setText("Running");
            sessionInfoLabel.setText("Running break");
            MyTimer timer = new MyTimer(1, minutesLabel, secondsLabel, button, "Break", sessionCounter, task.getNumOfSessions());
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
    void closeBtnClicked(MouseEvent event) {
        PomodoroPageController.modalPaneForPomodoroPage.hide(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        MyTimer timer = new MyTimer(2, minutesLabel, secondsLabel);
//        timer.t.setDaemon(true);
//        timer.t.start();
        closeBtnImg.setVisible(false);

    }
}


