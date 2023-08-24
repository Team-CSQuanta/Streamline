package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.UserInformation;
import com.csquanta.streamline.Networking.ChallengeInfo;
import com.csquanta.streamline.Networking.NetworkInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import static com.csquanta.streamline.Controllers.ChallengeController.networkUtil;
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

        ChallengeInfo acceptedChallenge = new ChallengeInfo();
        acceptedChallenge.setAccepted(true);

        acceptedChallenge.setEmail(challengeController.loadClientInfoFromFile()); // Set sender email
        System.out.println("After accept button clicked :" +challengeController.loadClientInfoFromFile());

        String receiverEmail = UserInformation.userInfo.getRequestSenderEmaill();
        System.out.println("in Challenge Request sender "+ receiverEmail) ;

        acceptedChallenge.setReceiverEmail(receiverEmail); // Set receiver email



        try {
            System.out.println("forward "+ acceptedChallenge);
            networkUtil.write(acceptedChallenge);

        } catch (Exception e){
            e.printStackTrace();
        }
        modalPaneForChallengeRequest.hide(true);
    }

    @FXML
    void onDeclineBtnClicked(ActionEvent event) {

    }

}