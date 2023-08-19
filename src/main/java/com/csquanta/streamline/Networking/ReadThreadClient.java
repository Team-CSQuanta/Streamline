package com.csquanta.streamline.Networking;

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

                Platform.runLater(() -> {

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/ChallengeRequest.fxml"));
                        VBox challenge = loader.load();
                         controller = loader.getController();

                        StackPane.setAlignment(challenge, Pos.BOTTOM_CENTER);
                        App.root.getChildren().add(challenge);
                        ZoomIn zoomIn = new ZoomIn();
                        zoomIn.setNode(challenge);
                        zoomIn.setSpeed(3);
                        zoomIn.play();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    if ("Build consistency".equals(challengeType)) {
                        controller.ChallengeType.setText(challengeType);
                        controller.PomoSession.setText(pomodoroSession);
                        controller.taskDescription.setText(challengeDescription);
                        controller.taskTag.setText(taskTag);
                        controller.monsterName.setText(monsterName);
                    } else {
                        controller.ChallengeType.setText(challengeType);
                        controller.taskDescription.setText(challengeDescription);
                        controller.monsterName.setText(monsterName);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
