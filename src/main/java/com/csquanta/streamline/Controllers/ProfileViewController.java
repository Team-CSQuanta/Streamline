package com.csquanta.streamline.Controllers;

import animatefx.animation.*;
import com.csquanta.streamline.Models.StaticUserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;

public class ProfileViewController implements Initializable {
    @FXML
    private AnchorPane anchorPaneProfileImg;
    @FXML
    private ImageView avatarArmor;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarBody.setImage(StaticUserInformation.avatarImageBody);
        avatarHead.setImage(StaticUserInformation.avatarImageHead);
        avatarHair.setImage(StaticUserInformation.avatarImageHair);
        image_bg.setImage(StaticUserInformation.avatarImageBg);
        priceLabel.setText(StaticUserInformation.totalGoldCoins.toString());
    }
}
