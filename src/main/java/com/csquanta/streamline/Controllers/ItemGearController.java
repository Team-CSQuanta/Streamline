package com.csquanta.streamline.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ItemGearController {

    @FXML
    private Button itemBuyButton;

    @FXML
    private VBox itemContainer;

    @FXML
    private ImageView itemImg;

    @FXML
    private Label itemLabel;

    @FXML
    private Label itemPrice;

    @FXML
    void buyBtnClicked(ActionEvent event) {

    }

    public ImageView getItemImg() {
        return itemImg;
    }

    public void setItemImg(ImageView itemImg) {
        this.itemImg = itemImg;
    }

    public Label getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(Label itemLabel) {
        this.itemLabel = itemLabel;
    }

    public Label getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Label itemPrice) {
        this.itemPrice = itemPrice;
    }
}
