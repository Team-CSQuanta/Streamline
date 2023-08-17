package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CustomizeBlockPetController {
    private String petPath;

    public void setPetPath(String path) {
        this.petPath = path;
    }
    public String getPetPath() {
        return petPath;
    }
    @FXML
    private ImageView componentImg;

    @FXML
    private VBox mainContainer;

    @FXML
    void customizeBlockClicked(MouseEvent event) {

    }

    @FXML
    void mouseEnteredInBlock(MouseEvent event) {

    }
    public void setCustomizePetData(Image image){
        componentImg.setImage(image);
    }

}
