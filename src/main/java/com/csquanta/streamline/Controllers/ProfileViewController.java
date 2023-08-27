package com.csquanta.streamline.Controllers;

import animatefx.animation.ZoomIn;
import com.csquanta.streamline.Models.StaticUserInformation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import com.csquanta.streamline.Models.UserInformation;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;
import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class ProfileViewController implements Initializable {
    @FXML
    private AnchorPane anchorPaneProfileImg;
    @FXML
    private ImageView avatarArmor;


    @FXML
    private ProgressBar HealthProgress;


    @FXML
    private ImageView avatarBody;

    @FXML
    private ImageView avatarHair;

    @FXML
    private ImageView avatarHead;

    @FXML
    private ImageView avatarPet;

    @FXML
    private ImageView headGear;

    @FXML
    private ImageView image_bg;

    @FXML
    private Label userName;

    @FXML
    private Label priceLabel;

    @FXML
    void closeUserProfileView(MouseEvent event) {
        modalPaneForHeader.hide(true);
    }

    @FXML
    void EditProfilePicture(ActionEvent event) throws IOException {
        VBox profileEditor = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/ProfileEdit.fxml")));
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(profileEditor);
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(profileEditor);
        zoomIn.setSpeed(3);
        zoomIn.play();
    }

    public void updateHealthProgress(int updatedHealth) {
        Platform.runLater(() -> {
        double healthPercentage =  (double) updatedHealth / 100;
        HealthProgress.setProgress(healthPercentage);

        });

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarBody.setImage(StaticUserInformation.avatarImageBody);
        avatarHead.setImage(StaticUserInformation.avatarImageHead);
        avatarHair.setImage(StaticUserInformation.avatarImageHair);
        image_bg.setImage(StaticUserInformation.avatarImageBg);
        avatarArmor.setImage(StaticUserInformation.avatarImageArmor);
        headGear.setImage(StaticUserInformation.avatarImageHeadGear);
        priceLabel.setText(String.format("%.2f", StaticUserInformation.totalGoldCoins));
        avatarPet.setImage(StaticUserInformation.avatarImagePet);
        userName.setText(userInfo.getDisplayName());
        HealthProgress.setProgress(userInfo.getUserHealth()/100.0);


    }
}
