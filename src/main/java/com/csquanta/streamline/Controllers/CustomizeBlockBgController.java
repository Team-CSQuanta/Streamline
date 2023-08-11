package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CustomizeBlockBgController {
    private String bgPath;
    @FXML
    private ImageView bgImage;

    @FXML
    private VBox mainContainer;
    public void setBgPath(String path) {
        this.bgPath = path;
    }
    public String getBgPath() {
        return bgPath;
    }
    public void setCustomizeBlockBgData(Image image){
        bgImage.setImage(image);
    }
}
