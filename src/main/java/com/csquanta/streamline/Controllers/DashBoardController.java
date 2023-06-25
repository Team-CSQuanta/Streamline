package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private Circle image;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image imageSrc = new Image("/Images/Avatar5.png");
        ImagePattern imagePattern = new ImagePattern(imageSrc);
        image.setFill(imagePattern);
    }

    @FXML
    private Label Userlabel;

    @FXML
    public void getUser(String user) {
        Userlabel.setText(user);
    }


}
