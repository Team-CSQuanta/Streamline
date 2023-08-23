package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Networking.ChallengeInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class ChatBoxController implements Initializable {

    @FXML
    private GridPane gridPane;

    private int currentRow = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void addChatMessage(Object message) {


        Platform.runLater(() -> {
            try {

                FXMLScene  chatBox = FXMLScene.load("/Fxml/ChatBox.fxml");
                ChatBoxController chatBoxController = (ChatBoxController)chatBox.controller;
                System.out.println("creatorControl " +chatBoxController);
                System.out.println("Shouting1");

                if (message instanceof ChallengeInfo) {
                    FXMLScene scene1 = FXMLScene.load("/Fxml/ReceiverChatBlock.fxml");
                    ReceiverChatBlockController receiverController = (ReceiverChatBlockController) scene1.controller;
                    receiverController.setLabel(new Label("1"));
                    chatBoxController.gridPane.add(scene1.root, 0, currentRow);
                    System.out.println("Shouting2");

                } else {
                    FXMLScene scene = FXMLScene.load("/Fxml/SenderChatBlock.fxml");
                    SenderChatBlockController senderController = (SenderChatBlockController) scene.controller;
                    senderController.setLabel(new Label("2"));
                    chatBoxController.gridPane.add(scene.root, 1, currentRow);
                    System.out.println("Shouting3");

                    System.out.println("Children "+chatBoxController.gridPane.getChildren().size());
                }

                currentRow++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
