package com.csquanta.streamline.Controllers;

import animatefx.animation.*;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Models.ChallengeLog;
import com.csquanta.streamline.Models.MyTimer;
import com.csquanta.streamline.Models.Task;
import com.csquanta.streamline.Models.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class PomodoroPageController implements Initializable {
    public static ModalPane modalPaneForPointsNotification = new ModalPane();

    @FXML
    private ImageView exitFromSession;

    @FXML
    void exitBtnClicked(MouseEvent event) {
        double randomPoints = Math.random()*((task.getNumOfSessions()*20));
        UserInformation.userInfo.deductGoldCoins(randomPoints);
        PomodoroPageController.modalPaneForPomodoroPage.hide(true);
    }

    @FXML
    void onExitImageEntered(MouseEvent event) {
        new Shake(this.exitFromSession).play();
    }

    @FXML
    private VBox pomodoroPageContainerVBox;

    @FXML
    private StackPane stackPanePomodoroContainer;
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
    void buttonClicked(ActionEvent event) throws IOException {
        if(button.getText().equals("Start session")){
            button.setText("Running");
            sessionInfoLabel.setText("Session " + (sessionCounter + 1));
            MyTimer timer = new MyTimer(25, minutesLabel, secondsLabel, button, "Session", sessionCounter, task.getNumOfSessions(), stackPanePomodoroContainer);
            timer.t.setDaemon(true);
            timer.t.start();
            sessionCounter++;
        }
        else if(button.getText().equals("Take Break") && sessionCounter != task.getNumOfSessions()){
            button.setText("Running");
            sessionInfoLabel.setText("Running break");
            MyTimer timer = new MyTimer(1, minutesLabel, secondsLabel, button, "Break", sessionCounter, task.getNumOfSessions(), stackPanePomodoroContainer);
            timer.t.setDaemon(true);
            timer.t.start();
        }
        else if(button.getText().equals("Finish")){
            button.setVisible(false);
            afterCompletingTaskReward();
            new FadeOut(button).play();
            sessionCounter = 0;

            // For Challenge log
            if(UserInformation.userInfo.getChallengeMode()){
                String descriptionMsg = "has damaged the monster's health by completing the task titled";
                InputStream stream = new FileInputStream("src/main/resources/com/example/javafxchatting/ProfileImage.png");
                ChallengeLog log = new ChallengeLog("PutUserNameHere", "PutRealNameHere", descriptionMsg + "\"" + task.getTaskTitle() + "\"", new Image(stream), task.getTaskTitle());
                ChallengeLog.staticChallengeLog.getChallengeLogs().add(log);
            }

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
        Tooltip.install(exitFromSession.getParent(), new Tooltip("Will deduct your gold coins!"));
        closeBtnImg.setVisible(false);
        stackPanePomodoroContainer.getChildren().add(modalPaneForPointsNotification);

    }
    public static void smallRewardAfterEachSession(StackPane container) throws IOException {
        FXMLScene fxmlScene = FXMLScene.load("/Fxml/GotPoints.fxml");
        GotPointsController controller = (GotPointsController) fxmlScene.controller;
        double randomPoints = Math.random()*20;
        controller.setRewardAward(String.format("%.2f", randomPoints));
        UserInformation.userInfo.addGoldCoins(randomPoints);
        modalPaneForPointsNotification.show(fxmlScene.root);
        new Pulse(fxmlScene.root).play();
    }
    public void afterCompletingTaskReward() throws IOException {
        FXMLScene fxmlScene = FXMLScene.load("/Fxml/GotPoints.fxml");
        GotPointsController controller = (GotPointsController) fxmlScene.controller;
        double randomPoints = task.getNumOfSessions()*80;
        controller.setRewardAward(String.format("%.2f", randomPoints));
        UserInformation.userInfo.addGoldCoins(randomPoints);
        modalPaneForPointsNotification.show(fxmlScene.root);
        new Pulse(fxmlScene.root).play();
    }

}


