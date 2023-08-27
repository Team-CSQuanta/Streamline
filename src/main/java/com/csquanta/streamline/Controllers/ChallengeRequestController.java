package com.csquanta.streamline.Controllers;

import animatefx.animation.ZoomIn;
import com.csquanta.streamline.App;
import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Models.UserInformation;
import com.csquanta.streamline.Networking.ChallengeParticipantsInfo;
import com.csquanta.streamline.Networking.ChallengeResponse;
import com.csquanta.streamline.Networking.NetworkInformation;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
    @FXML
    private Label participantsName;
    ChallengeController challengeController = new ChallengeController();


    private ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap;

    public ChallengeRequestController() throws IOException {
    }

    @FXML
    void onAcceptBtnClicked(ActionEvent event) throws IOException {
        // need to change topHbox and bottom vBox
        ChallengeUI.challengeUI.setChallengeMode(true);
        ChallengeUI.challengeUI.newLoadForChallengeUI();
        // Participants user information storing
        ChallengeParticipantsInfo.challengeParticipantsInfo.setParticipantsName(participantsName.getText());



        // Participants avatar image saving
        WritableImage image = profileArea.snapshot(new SnapshotParameters(), null);
        ChallengeParticipantsInfo.challengeParticipantsInfo.setParticipantsImageFile(new File(participantsName.getText() + "ChallengeImage.png"));
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", ChallengeParticipantsInfo.challengeParticipantsInfo.getParticipantsImageFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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