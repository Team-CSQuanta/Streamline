package com.csquanta.streamline.Models;

import animatefx.animation.ZoomIn;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Controllers.*;
import com.csquanta.streamline.Networking.ChallengeTaskLog;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class ChallengeUI {
    public static ChallengeUI challengeUI= new ChallengeUI();
    private ChallengeLogController challengeLogController;
    private VBox challengeLog;
    private boolean challengeMode;
    private boolean pendingStatus;
    private String requestSenderEmail;
    private ChallengeController challengeController;
    private VBox challengePage;
    private MonsterInChallengeController monsterInChallengeController;
    private HBox monsterInChallengePage;
    private VBox notHavingAnyChallengePage;

    private VBox challengeRequestSentPage;
    public ChallengeUI(){
        try {
            FXMLScene scene = FXMLScene.load("/Fxml/Challenge.fxml");
            FXMLScene  challengeLogPage = FXMLScene.load("/Fxml/ChallengeLog.fxml");
            FXMLScene challengeMonster = FXMLScene.load("/Fxml/MonsterInChallenge.fxml");
            FXMLScene noChallengeScene = FXMLScene.load("/Fxml/NotHavingAnyChallenge.fxml");
            FXMLScene challengeRequestSent = FXMLScene.load("/Fxml/ChallengeRequestSent.fxml");
            challengeRequestSentPage = (VBox) challengeRequestSent.root;
            notHavingAnyChallengePage = (VBox) noChallengeScene.root;
            monsterInChallengePage = (HBox) challengeMonster.root;
            monsterInChallengeController = (MonsterInChallengeController) challengeMonster.controller;
            challengeLog = (VBox) challengeLogPage.root;
            challengeLogController = (ChallengeLogController) challengeLogPage.controller;
            challengeController = (ChallengeController) scene.controller;
            challengePage = (VBox) scene.root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VBox getChallengeRequestSentPage() {
        return challengeRequestSentPage;
    }

    public void setChallengeRequestSentPage(VBox challengeRequestSentPage) {
        this.challengeRequestSentPage = challengeRequestSentPage;
    }

    public MonsterInChallengeController getMonsterInChallengeController() {
        return monsterInChallengeController;
    }

    public void setMonsterInChallengeController(MonsterInChallengeController monsterInChallengeController) {
        this.monsterInChallengeController = monsterInChallengeController;
    }

    public HBox getMonsterInChallengePage() {
        return monsterInChallengePage;
    }

    public void setMonsterInChallengePage(HBox monsterInChallengePage) {
        this.monsterInChallengePage = monsterInChallengePage;
    }

    public VBox getNotHavingAnyChallengePage() {
        return notHavingAnyChallengePage;
    }

    public void setNotHavingAnyChallengePage(VBox notHavingAnyChallengePage) {
        this.notHavingAnyChallengePage = notHavingAnyChallengePage;
    }

    public VBox getChallengeLog() {
        return challengeLog;
    }

    public void setChallengeLog(VBox challengeLog) {
        this.challengeLog = challengeLog;
    }

    public ChallengeLogController getChallengeLogController() {
        return challengeLogController;
    }

    public void setChallengeLogController(ChallengeLogController challengeLogController) {
        this.challengeLogController = challengeLogController;
    }

    public VBox getChallengePage() {
        return challengePage;
    }

    public void setChallengePage(VBox challengePage) {
        this.challengePage = challengePage;
    }

    public ChallengeController getChallengeController() {
        return challengeController;
    }

    public void setChallengeController(ChallengeController challengeController) {
        this.challengeController = challengeController;
    }

    public String getRequestSenderEmail() {
        return requestSenderEmail;
    }

    public void setRequestSenderEmail(String requestSenderEmail) {
        this.requestSenderEmail = requestSenderEmail;
    }

    public boolean getChallengeMode(){
        return challengeMode;
    }
    public void setChallengeMode(boolean mode){
        challengeMode = mode;
    }
    public boolean isPending(){
        return  pendingStatus;
    }
    public void setPendingStatus(boolean status){
        pendingStatus = status;
    }
    public void addChallengeTaskLog(){
        int currentRow = 0;
        FXMLScene block;
        System.out.println(ChallengeTaskLog.taskLog.getChallengeTaskLogs().size() + " Challenge task log size");
        for(ChallengeTaskLog t: ChallengeTaskLog.taskLog.getChallengeTaskLogs()){

            if(t.getUserEmail().equals(userInfo.getEmail())){
                FXMLScene block = null;

           
                try {
                    block = FXMLScene.load("/Fxml/ChallengeBlockForSender.fxml");
                    ChallengeBlockController receiverController = (ChallengeBlockController) block.controller;


                    ChallengeUI.challengeUI.getChallengeLogController().getLogGridPane().add(block.root, 0, currentRow++);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    block = FXMLScene.load("/Fxml/ChallengeBlockForReceiver.fxml");
                    ChallengeBlockController receiverController = (ChallengeBlockController) block.controller;


                    ChallengeUI.challengeUI.getChallengeLogController().getLogGridPane().add(block.root, 1, currentRow++);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }

    public void newLoadForChallengeUI() throws IOException {
        App.newLoad();
        ChallengeController controller = challengeUI.getChallengeController();

        if(!challengeUI.getChallengeMode() && !challengeUI.isPending()){
            controller.getBottomVbox().getChildren().setAll(challengeUI.getNotHavingAnyChallengePage());
            StackPane.setAlignment(challengeUI.getChallengePage(), Pos.BOTTOM_CENTER);
            App.root.getChildren().add(challengeUI.getChallengePage());

        }else if(challengeUI.isPending() && !challengeUI.getChallengeMode()){
            controller.getBottomVbox().getChildren().setAll(challengeUI.getChallengeRequestSentPage());
            StackPane.setAlignment(challengeUI.getChallengePage(), Pos.BOTTOM_CENTER);
            App.root.getChildren().add(challengeUI.getChallengePage());
        }else{
            controller.getTopHbox().getChildren().setAll(challengeUI.getMonsterInChallengePage());
            controller.getBottomVbox().getChildren().setAll(challengeUI.getChallengeLog());
            if(ChallengeTaskLog.taskLog.getChallengeTaskLogs().size() > 0){
                challengeUI.addChallengeTaskLog();
            }

            StackPane.setAlignment(challengeUI.getChallengePage(), Pos.BOTTOM_CENTER);
            App.root.getChildren().add(challengeUI.getChallengePage());
        }
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(ChallengeUI.challengeUI.getChallengePage());
        zoomIn.setSpeed(3);
        zoomIn.play();
    }

}
