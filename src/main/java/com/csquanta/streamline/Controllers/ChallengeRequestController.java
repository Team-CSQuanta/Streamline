package com.csquanta.streamline.Controllers;

import animatefx.animation.ZoomIn;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Models.UserInformation;
import com.csquanta.streamline.Networking.ChallengeResponse;
import com.csquanta.streamline.Networking.NetworkInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import static com.csquanta.streamline.Controllers.ChallengeController.networkUtil;
import static com.csquanta.streamline.Models.UserInformation.userInfo;
import static com.csquanta.streamline.Networking.ReadThreadClient.modalPaneForChallengeRequest;


public class ChallengeRequestController {

    @FXML
    public Label ChallengeType;

    @FXML
    public Label PomoSession;

    @FXML
    public Label TaskTitle;

    @FXML
    private Button acceptBtn;

    @FXML
    public ImageView avatarArmor;

    @FXML
    public ImageView avatarBody;

    @FXML
    public ImageView avatarHair;

    @FXML
    public ImageView avatarHead;

    @FXML
    public ImageView avatarPet;

    @FXML
    public ImageView headGear;

    @FXML
    public ImageView image_bg;

    @FXML
    private Button decline;


    @FXML
    public Label monsterName;

    @FXML
    private AnchorPane profileArea;

    @FXML
    public Label taskDescription;

    @FXML
    public Label taskTag;
    @FXML
    public Label SessionNo;
    @FXML
    public Label Ttag;
    ChallengeController challengeController = new ChallengeController();


    private ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap;

    public ChallengeRequestController() throws IOException {
    }

    @FXML
    void onAcceptBtnClicked(ActionEvent event) throws IOException {
        // need to change topHbox and bottom vBox

//        ChallengeController controller = UserInformation.userInfo.getChallengeController();

//        FXMLScene  challengeLog = FXMLScene.load("/Fxml/ChallengeLog.fxml");
//        ChallengeLogController challengeLogController = (ChallengeLogController) challengeLog.controller;
//        controller.getBottomVbox().getChildren().setAll(challengeLog.root);
//
//        ZoomIn zoomIn = new ZoomIn();
//        zoomIn.setNode(challengeLog.root);
//        zoomIn.setSpeed(3);
//        zoomIn.play();
//
//        FXMLScene ChallengedMonster = FXMLScene.load("/Fxml/MonsterInChallenge.fxml");
//        controller.getTopHbox().getChildren().setAll(ChallengedMonster.root);
//        zoomIn.setNode(ChallengedMonster.root);
//        zoomIn.setSpeed(3);
//        zoomIn.play();


        App.newLoad();
        ChallengeUI.challengeUI.setChallengeMode(true);
        ChallengeUI.challengeUI.setPendingStatus(false);
        ChallengeController controller = ChallengeUI.challengeUI.getChallengeController();

        // ChallengeLog Bottom Vbox;
        FXMLScene  challengeLog = FXMLScene.load("/Fxml/ChallengeLog.fxml");
        ChallengeLogController challengeLogController = (ChallengeLogController) challengeLog.controller;
        controller.getBottomVbox().getChildren().setAll(challengeLog.root);


        FXMLScene ChallengedMonster = FXMLScene.load("/Fxml/MonsterInChallenge.fxml");
        controller.getTopHbox().getChildren().setAll(ChallengedMonster.root);

        StackPane.setAlignment(ChallengeUI.challengeUI.getChallengePage(), Pos.BOTTOM_CENTER);
        App.root.getChildren().add(ChallengeUI.challengeUI.getChallengePage());
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(ChallengeUI.challengeUI.getChallengePage());
        zoomIn.setSpeed(3);
        zoomIn.play();



        String receiverEmail = ChallengeUI.challengeUI.getRequestSenderEmail();

        String responseMessage = "Your Challenge has been accepted!";

        ChallengeResponse challengeResponse = new ChallengeResponse(userInfo.getEmail(),receiverEmail,responseMessage);

        System.out.println("in Challenge Request sender "+ receiverEmail) ;


        try {

            networkUtil.write(challengeResponse);

        } catch (Exception e){
            e.printStackTrace();
        }
        modalPaneForChallengeRequest.hide(true);
    }

    @FXML
    void onDeclineBtnClicked(ActionEvent event) {

    }

}