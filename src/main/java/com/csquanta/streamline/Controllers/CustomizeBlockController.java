package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CustomizeBlockController {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @FXML
    private ImageView componentImg;

    @FXML
    private VBox mainContainer;

    @FXML
    void mouseEnteredInBlock(MouseEvent event) {

    }

    public void setCustomizeBlockData(Image image){
        componentImg.setImage(image);
    }

}
