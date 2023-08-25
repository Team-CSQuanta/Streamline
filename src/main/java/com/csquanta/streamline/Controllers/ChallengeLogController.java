package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Networking.ChallengeMessage;
import com.csquanta.streamline.Networking.ChallengeTaskLog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChallengeLogController implements Initializable {

    @FXML
    private VBox ChallengeLogVbox;

    @FXML
    private GridPane logGridPane;

    public GridPane getLogGridPane() {
        return logGridPane;
    }

    public void setLogGridPane(GridPane logGridPane) {
        this.logGridPane = logGridPane;
    }

//    public void addChatMessage(Object message) {
//
//
//        Platform.runLater(() -> {
//            try {
//
//                FXMLScene  chatBox = FXMLScene.load("/Fxml/ChallengeLog.fxml");
//                ChallengeLogController challengeLogController = (ChallengeLogController)chatBox.controller;
//                System.out.println("creatorControl " +challengeLogController);
//                System.out.println("Shouting1");
//
//                if (message instanceof ChallengeMessage) {
//                    FXMLScene scene1 = FXMLScene.load("/Fxml/ChallengeBlockForReceiver.fxml");
//                    ChallengeBlockController receiverController = (ChallengeBlockController) scene1.controller;
//                    challengeLogController.logGridPane.add(scene1.root, 0, currentRow);
//                    System.out.println("Shouting2");
//                } else {
//                    FXMLScene scene = FXMLScene.load("/Fxml/ChallengeBlockForSender.fxml");
//                    ChallengeBlockController senderController = (ChallengeBlockController) scene.controller;
//                    System.out.println("Children before adding: " + challengeLogController.logGridPane.getChildren().size());
//                    challengeLogController.logGridPane.add(scene.root, 1, currentRow);
//
//                    System.out.println("Shouting3");
//                    System.out.println("Children "+challengeLogController.logGridPane.getChildren().size());
//                }
//
//                currentRow++;
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}