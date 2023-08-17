package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AvatarBackgroundsPurchasedController {

    @FXML
    private HBox avatarBgMainContainer;

    @FXML
    private ImageView bgImage;

    @FXML
    private Label bgTitle;

    @FXML
    private ImageView successIconImage;
    public Label getItemTitle() {
        return bgTitle;
    }

    public void setItemTitle(Label itemLabel) {
        this.bgTitle.setText(itemLabel.getText());
    }

    public ImageView getBGPurchasedImg() {
        return bgImage;
    }

    public void setBGPurchasedImg(ImageView itemPurchasedImg) {
        this.bgImage.setImage(itemPurchasedImg.getImage());
    }
}
