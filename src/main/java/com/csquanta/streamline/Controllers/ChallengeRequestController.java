package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Networking.ChallengeInfoWhenParticipated;
import com.csquanta.streamline.Networking.ChallengeMessage;
import com.csquanta.streamline.Networking.ChallengeResponse;
import com.csquanta.streamline.Networking.NetworkInformation;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

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

    public Label getParticipantsName() {
        return participantsName;
    }

    public void setParticipantsName(String participantsName) {
        this.participantsName.setText(participantsName);
    }

    ChallengeController challengeController = new ChallengeController();


    private ConcurrentHashMap<String, NetworkInformation> clientNetworkInformationMap;

    public ChallengeRequestController() throws IOException {
    }

    @FXML
    void onAcceptBtnClicked(ActionEvent event) throws IOException {
        ChallengeUI.challengeUI.setChallengeMode(true);
        ChallengeUI.challengeUI.newLoadForChallengeUI();
        ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.setParticipantsName(participantsName.getText());
        System.out.println("In accept button (Participant name): " + participantsName.getText());

        ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.setSelectedMonsterName(monsterName.getText());
        // Participants avatar image saving
        WritableImage image = profileArea.snapshot(new SnapshotParameters(), null);
        ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.setParticipantsImageFile(new File(participantsName.getText() + "ChallengeImage.png"));

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getParticipantsImageFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String imagePath = "ProfileImage.png";

        String receiverEmail = ChallengeUI.challengeUI.getRequestSenderEmail();
        String responseMessage = "Your Challenge has been accepted!";



        System.out.println("in Challenge Request sender "+ receiverEmail) ;
        try {

            File imageFile = new File(imagePath);
            byte[] imageBytes = new byte[(int) imageFile.length()];
            try (FileInputStream fileInputStream = new FileInputStream(imageFile)) {
                int bytesRead = fileInputStream.read(imageBytes);
                if (bytesRead != -1) {
                    ChallengeResponse challengeResponse = new ChallengeResponse(userInfo.getEmail(),receiverEmail,responseMessage,imageBytes);
                    networkUtil.write(challengeResponse);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }




        } catch (Exception e){
            e.printStackTrace();
        }
        modalPaneForChallengeRequest.hide(true);
    }

    @FXML
    void onDeclineBtnClicked(ActionEvent event) {

    }

}