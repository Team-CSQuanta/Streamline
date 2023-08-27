package com.csquanta.streamline.Networking;

import animatefx.animation.Pulse;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.Controllers.ChallengeLogController;
import com.csquanta.streamline.Controllers.ChallengeRequestController;
import com.csquanta.streamline.Controllers.FXMLScene;
import com.csquanta.streamline.Models.ChallengeUI;
import javafx.application.Platform;
import javafx.geometry.Pos;

import java.io.FileOutputStream;
import java.io.IOException;

public class ReadThreadClient extends Thread {
    private NetworkUtil networkUtil;
    private String clientEmail;
    ChallengeRequestController controller;



    public static final ModalPane modalPaneForChallengeRequest = new ModalPane();
    ChallengeLogController chatBoxController = new ChallengeLogController();


    public ReadThreadClient(NetworkUtil networkUtil, String clientEmail) {
        this.networkUtil = networkUtil;
        this.clientEmail = clientEmail;
    }


    @Override
    public void run() {
        try {
            while (true) {
                Message receivedMessage = (Message) networkUtil.read();
                String sender = receivedMessage.getFrom();  // Challenge sender email

                ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.setParticipantsEmail(sender);


                if (receivedMessage.getMessageType() == MessageType.CHALLENGE) {

                    String pomodoroSession = ((ChallengeMessage) receivedMessage).getChallengeTaskPomodoroSession();
                    String challengeType = ((ChallengeMessage) receivedMessage).getChallengeType();
                    String challengeDescription = ((ChallengeMessage) receivedMessage).getChallengeDescription();
                    String taskTag = ((ChallengeMessage) receivedMessage).getChallengeTaskTag();
                    String monsterName = ((ChallengeMessage) receivedMessage).getMonstersName();
                    String taskTitle = ((ChallengeMessage) receivedMessage).getTaskTitle();

                    String challengeSenderName = ((ChallengeMessage) receivedMessage).getChallengeRequestSenderName();

                    byte[] imageData = ((ChallengeMessage) receivedMessage).getImageData();


                    ChallengeUI.challengeUI.setRequestSenderEmail(sender);
                    System.out.println("in read thread client "+ ChallengeUI.challengeUI.getRequestSenderEmail());


                    Platform.runLater(() -> {

                        try {
                            FXMLScene fxmlScene = FXMLScene.load("/Fxml/ChallengeRequest.fxml");
                            modalPaneForChallengeRequest.setAlignment(Pos.CENTER);
                            modalPaneForChallengeRequest.show(fxmlScene.root);
                            controller = (ChallengeRequestController) fxmlScene.controller;
                            Pulse pulse = new Pulse();
                            pulse.setNode(modalPaneForChallengeRequest);
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
                            controller.setParticipantsName(challengeSenderName);
                        } else {
                            controller.ChallengeType.setText(challengeType);
                            controller.taskDescription.setText(challengeDescription);
                            controller.monsterName.setText(monsterName);
                            controller.TaskTitle.setText(taskTitle);
                            controller.setParticipantsName(challengeSenderName);
                        }

                    });

                    String imagePath = "received_profile_image" + ".png";
                    try (FileOutputStream imageOutputStream = new FileOutputStream(imagePath)) {
                        imageOutputStream.write(imageData);
                        System.out.println("Image received and saved as " + imagePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }  else if (receivedMessage.getMessageType() == MessageType.CHALLENGE_RESPONSE ) {
                    ChallengeUI.challengeUI.setPendingStatus(false);
                    ChallengeUI.challengeUI.setChallengeMode(true);


                    Platform.runLater(() ->{
                        try {
                            ChallengeUI.challengeUI.newLoadForChallengeUI();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    String challengeResponse = ((ChallengeResponse) receivedMessage).getResponseMessage();
                    byte[] imageData = ((ChallengeResponse) receivedMessage).getImageData();
                    String imagePath = "received_profile_image" + ".png";
                    try (FileOutputStream imageOutputStream = new FileOutputStream(imagePath)) {
                        imageOutputStream.write(imageData);
                        System.out.println("Image received and saved as " + imagePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(challengeResponse);

                }else if(receivedMessage.getMessageType() == MessageType.CHALLENGE_UPDATE){

                    String title = ((ChallengeUpdate) receivedMessage).getTitle();
                    Platform.runLater(() ->{
                        ChallengeTaskLog task = new ChallengeTaskLog("Jubair", sender, title);
                        ChallengeTaskLog.taskLog.getChallengeTaskLogs().add(task);
                        try {
                            ChallengeUI.challengeUI.newLoadForChallengeUI();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });

                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
