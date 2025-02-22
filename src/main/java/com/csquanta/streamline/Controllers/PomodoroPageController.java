package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.Pulse;
import animatefx.animation.Shake;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Models.MyTimer;
import com.csquanta.streamline.Models.Task;
import com.csquanta.streamline.Networking.ChallengeInfoWhenParticipated;
import com.csquanta.streamline.Networking.ChallengeTaskLog;
import com.csquanta.streamline.Networking.ChallengeUpdate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.ChallengeController.networkUtil;
import static com.csquanta.streamline.Models.UserInformation.userInfo;


public class PomodoroPageController implements Initializable {
    static ItemGearController itemGearController = new ItemGearController();
    public static ModalPane modalPaneForPointsNotification = new ModalPane();

    @FXML
    private ImageView exitFromSession;

    public PomodoroPageController() throws FileNotFoundException {
    }

    @FXML
    void exitBtnClicked(MouseEvent event) {
        double randomPoints = Math.random()*((task.getNumOfSessions()*20));
        sessionCounter = 0;
        userInfo.deductGoldCoins(randomPoints);
        PomodoroPageController.modalPaneForPomodoroPage.hide(true);
        itemGearController.soundPlayer("/Sounds/pop-94319.mp3");

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
    ChallengeCreatorController challengeCreatorController= new ChallengeCreatorController();

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
            MyTimer timer = new MyTimer(Integer.parseInt(userInfo.getPomodoroSessionTime()), minutesLabel, secondsLabel, button, "Session", sessionCounter, task.getNumOfSessions(), stackPanePomodoroContainer);
            timer.t.setDaemon(true);
            timer.t.start();
            sessionCounter++;

        }
        else if(button.getText().equals("Take Break") && sessionCounter != task.getNumOfSessions()){
            button.setText("Running");
            sessionInfoLabel.setText("Running break");
            MyTimer timer = new MyTimer(Integer.parseInt(userInfo.getBreakTime()), minutesLabel, secondsLabel, button, "Break", sessionCounter, task.getNumOfSessions(), stackPanePomodoroContainer);
            timer.t.setDaemon(true);
            timer.t.start();
        }
        else if(button.getText().equals("Finish")){
            //user health should be updated
            int userHealth = userInfo.getUserHealth();
            if (userHealth!=100){
                userHealth = userHealth+2;
                userInfo.setUserHealth(userHealth);
                System.out.println("User health incremented to "+ userInfo.getUserHealth());
            }
            button.setVisible(false);
            afterCompletingTaskReward();
            new FadeOut(button).play();
            sessionCounter = 0;
            task.setCompleted(true);

            // For Challenge log
            if(ChallengeUI.challengeUI.getChallengeMode()){

                ChallengeUI.challengeUI.deductMonsterHealth(task.getNumOfSessions());
                ChallengeUpdate challengeUpdate = new ChallengeUpdate(userInfo.getEmail(), ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getParticipantsEmail(),task.getTaskTitle(), task.getNumOfSessions());



                //ChallengeUpdate challengeUpdate = new ChallengeUpdate(userInfo.getEmail(), ChallengeParticipantsInfo.challengeParticipantsInfo.getParticipantsEmail(),task.getTaskTitle());

                ChallengeTaskLog challengeTask = new ChallengeTaskLog(userInfo.getDisplayName(), userInfo.getEmail(), task.getTaskTitle());

                ChallengeTaskLog.taskLog.getChallengeTaskLogs().add(challengeTask);
                networkUtil.write(challengeUpdate);
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
        userInfo.addGoldCoins(randomPoints);
        modalPaneForPointsNotification.show(fxmlScene.root);
        new Pulse(fxmlScene.root).play();
        itemGearController.soundPlayer("/Sounds/mixkit-achievement-completed-2068.wav");

    }
    public void afterCompletingTaskReward() throws IOException {
        FXMLScene fxmlScene = FXMLScene.load("/Fxml/GotPoints.fxml");
        GotPointsController controller = (GotPointsController) fxmlScene.controller;
        double randomPoints = task.getNumOfSessions()*80;
        controller.setRewardAward(String.format("%.2f", randomPoints));
        userInfo.addGoldCoins(randomPoints);
        modalPaneForPointsNotification.show(fxmlScene.root);
        new Pulse(fxmlScene.root).play();
        itemGearController.soundPlayer("/Sounds/gold-coin-prize.wav");

    }

}


