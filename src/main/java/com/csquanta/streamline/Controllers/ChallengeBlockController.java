package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class ChallengeBlockController {

    @FXML
    private Label descriptionMsg;

    @FXML
    private Circle imageCircle;

    @FXML
    private Label nameLabel;

    @FXML
    void dragOverTheChatBlock(MouseEvent event) {

    }
    public void setDescriptionMsg(String description){
        descriptionMsg.setText(description);
    }
    public void setUserName(String name){
        nameLabel.setText(name);
    }
    public void setUserEmail(String imagePath){
        InputStream stream = null;
        try {
            stream = new FileInputStream(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image im = new Image(stream);
        imageCircle.setFill(new ImagePattern(im));
    }
}
