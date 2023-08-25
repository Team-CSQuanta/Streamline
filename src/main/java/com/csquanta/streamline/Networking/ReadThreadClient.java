package com.csquanta.streamline.Networking;

import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Controllers.*;
import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Models.UserInformation;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

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
                ChallengeParticipantsInfo.participantsEmail = sender;
                if (receivedMessage.getMessageType() == MessageType.CHALLENGE) {

                    String pomodoroSession = ((ChallengeMessage) receivedMessage).getChallengeTaskPomodoroSession();
                    String challengeType = ((ChallengeMessage) receivedMessage).getChallengeType();
                    String challengeDescription = ((ChallengeMessage) receivedMessage).getChallengeDescription();
                    String taskTag = ((ChallengeMessage) receivedMessage).getChallengeTaskTag();
                    String monsterName = ((ChallengeMessage) receivedMessage).getMonstersName();
                    String taskTitle = ((ChallengeMessage) receivedMessage).getTaskTitle();

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
//                        String imageBg = UserInformation.userInfo.getAvatarImageBg();
//                        String imagePet = UserInformation.userInfo.getAvatarImagePet();
//                        String imageHeadGear = UserInformation.userInfo.getAvatarImageHeadGear();
//                        String imageHead = UserInformation.userInfo.getAvatarImageHead();
//                        String imageArmor = UserInformation.userInfo.getAvatarImageArmor();
//                        String imageHair = UserInformation.userInfo.getAvatarImageHair();
//                        String imageBody = UserInformation.userInfo.getAvatarImageBody();
//
//                        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageBg)));
//                        controller.image_bg.setImage(bgImage);
//
//                        Image petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePet)));
//                        controller.avatarPet.setImage(petImage);
////                        Image HeadGearImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageHeadGear)));
////                        controller.headGear.setImage(HeadGearImage);
//                        Image HeadImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageHead)));
//                        controller.avatarHead.setImage(HeadImage);
//                        Image ArmorImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageArmor)));
//                        controller.avatarArmor.setImage(ArmorImage);
//                        Image HairImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageHair)));
//                        controller.avatarHair.setImage(HairImage);
//                        Image BodyImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imageBody)));
//                        controller.avatarBody.setImage(BodyImage);


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
