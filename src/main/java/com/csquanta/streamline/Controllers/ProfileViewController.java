package com.csquanta.streamline.Controllers;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Objects;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;

public class ProfileViewController {

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
    void closeUserProfileView(MouseEvent event) {
        modalPaneForHeader.hide(true);
    }

    @FXML
    void EditProfilePicture(ActionEvent event) throws IOException {
        VBox profileEditor = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/ProfileEdit.fxml")));
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(profileEditor);
        FadeIn fadeIn = new FadeIn();
        fadeIn.setNode(profileEditor);
        fadeIn.play();
    }
}
