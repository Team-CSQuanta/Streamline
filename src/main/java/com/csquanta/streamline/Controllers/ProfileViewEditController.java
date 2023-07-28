package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProfileViewEditController {

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
        HeaderController.modalPaneForHeader.hide(true);
    }

}
