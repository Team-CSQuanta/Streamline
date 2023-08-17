package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ItemPurchasedSuccessFullyController {

    @FXML
    private Label itemLabel;

    @FXML
    private VBox itemPurChasedContainer;

    @FXML
    private ImageView itemPurchasedImg;

    public Label getItemLabel() {
        return itemLabel;
    }

     public void setItemLabel(Label itemLabel) {
        this.itemLabel.setText(itemLabel.getText());
    }

    public ImageView getItemPurchasedImg() {
        return itemPurchasedImg;
    }

    public void setItemPurchasedImg(ImageView itemPurchasedImg) {
        this.itemPurchasedImg.setImage(itemPurchasedImg.getImage());
    }
}
