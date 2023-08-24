package com.csquanta.streamline.Networking;

import animatefx.animation.Pulse;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.Controllers.*;
import com.csquanta.streamline.Models.UserInformation;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;

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
                String sender = receivedMessage.getFrom();
                if (receivedMessage.getMessageType() == MessageType.CHALLENGE) {

                    String pomodoroSession = ((ChallengeMessage) receivedMessage).getChallengeTaskPomodoroSession();
                    String challengeType = ((ChallengeMessage) receivedMessage).getChallengeType();
                    String challengeDescription = ((ChallengeMessage) receivedMessage).getChallengeDescription();
                    String taskTag = ((ChallengeMessage) receivedMessage).getChallengeTaskTag();
                    String monsterName = ((ChallengeMessage) receivedMessage).getMonstersName();
                    String taskTitle = ((ChallengeMessage) receivedMessage).getTaskTitle();

                    UserInformation.userInfo.setRequestSenderEmaill(sender);
                    System.out.println("in read thread client "+ UserInformation.userInfo.getRequestSenderEmaill());


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
//                        Image bgImage = new Image(getClass().getResourceAsStream(imageBg));
//                        controller.image_bg.setImage(bgImage);
//
//                        Image petImage = new Image(getClass().getResourceAsStream(imagePet));
//                        controller.avatarPet.setImage(petImage);
//                        Image HeadGearImage = new Image(getClass().getResourceAsStream(imageHeadGear));
//                        controller.headGear.setImage(HeadGearImage);
//                        Image HeadImage = new Image(getClass().getResourceAsStream(imageHead));
//                        controller.avatarHead.setImage(HeadImage);
//                        Image ArmorImage = new Image(getClass().getResourceAsStream(imageArmor));
//                        controller.avatarArmor.setImage(ArmorImage);
//                        Image HairImage = new Image(getClass().getResourceAsStream(imageHair));
//                        controller.avatarHair.setImage(HairImage);
//                        Image BodyImage = new Image(getClass().getResourceAsStream(imageBody));
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
                        System.out.println("Checking");
                    });
                }  else if (receivedMessage.getMessageType() == MessageType.CHALLENGE_RESPONSE ) {
                    String challengeResponse = ((ChallengeResponse) receivedMessage).getResponseMessage();
                    Platform.runLater(() -> chatBoxController.addChatMessage(receivedMessage));


                   System.out.println("Received Message: " + challengeResponse);

                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

}
