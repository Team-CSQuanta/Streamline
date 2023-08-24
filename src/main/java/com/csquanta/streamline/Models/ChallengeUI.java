package com.csquanta.streamline.Models;

import com.csquanta.streamline.Controllers.ChallengeController;
import com.csquanta.streamline.Controllers.FXMLScene;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChallengeUI {
    public static ChallengeUI challengeUI= new ChallengeUI();
    private boolean challengeMode;
    private boolean pendingStatus;
    private String requestSenderEmail;
    private ChallengeController challengeController;
    private VBox challengePage;
    public ChallengeUI(){
        try {
            FXMLScene scene = FXMLScene.load("/Fxml/Challenge.fxml");
            challengeController = (ChallengeController) scene.controller;
            challengePage = (VBox) scene.root;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
