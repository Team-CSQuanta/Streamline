package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CustomizeBlockController {

    @FXML
    private ImageView componentImg;

    @FXML
    private VBox mainContainer;

    @FXML
    void mouseEnteredInBlock(MouseEvent event) {

    }

    @FXML
    void setComponent(MouseEvent event) throws IOException {
        FXMLScene fxmlScene = FXMLScene.load("/Fxml/ProfileEdit.fxml");
        ProfileEditController profileEditController = (ProfileEditController) fxmlScene.controller;
        profileEditController.setAvatarBody(componentImg);
        System.out.println("Set image");
    }
    public void setCustomizeBlockDate(Image image){
        componentImg.setImage(image);
    }
}
