package com.csquanta.streamline.Networking;

import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Controllers.ChallengeRequestController;
import com.csquanta.streamline.Controllers.FXMLScene;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;
import static java.util.Objects.requireNonNull;

public class ReadThreadClient extends Thread {
    private NetworkUtil networkUtil;
    private String clientEmail;
    ChallengeRequestController controller;



    public ReadThreadClient(NetworkUtil networkUtil, String clientEmail) throws IOException {
        this.networkUtil = networkUtil;
        this.clientEmail = clientEmail;
    }


    @Override
    public void run() {
        try {
            while (true) {
                ChallengeInfo receivedMessage = (ChallengeInfo) networkUtil.read();
                String pomodoroSession = receivedMessage.getChallengeTaskPomodoroSession();
                String challengeType = receivedMessage.getChallengeType();
                String challengeDescription = receivedMessage.getChallengeDescription();
                String taskTag = receivedMessage.getChallengeTaskTag();
                String monsterName = receivedMessage.getMonstersName();
                String taskTitle = receivedMessage.getTaskTitle();

                Platform.runLater(() -> {

                    try {

                        FXMLScene fxmlScene = FXMLScene.load("/Fxml/ChallengeRequest.fxml");

                        modalPaneForHeader.setAlignment(Pos.CENTER);
                        modalPaneForHeader.show(fxmlScene.root);
                        controller = (ChallengeRequestController) fxmlScene.controller;
                        Pulse pulse = new Pulse();
                        pulse.setNode(modalPaneForHeader);
                        pulse.play();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    if ("Build consistency".equals(challengeType)) {
                        controller.ChallengeType.setText(challengeType);
                        controller.PomoSession.setText(pomodoroSession);
                        controller.taskDescription.setText(challengeDescription);
                        controller.taskTag.setText(taskTag);
                        controller.monsterName.setText(monsterName);
                        controller.SessionNo.setText("Session               :");
                        controller.Ttag.setText("Task tag             :");
                        controller.TaskTitle.setText(taskTitle);
                    } else {
                        controller.ChallengeType.setText(challengeType);
                        controller.taskDescription.setText(challengeDescription);
                        controller.monsterName.setText(monsterName);
                        controller.TaskTitle.setText(taskTitle);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
