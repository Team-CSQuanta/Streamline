package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

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
    public void setData(Item item){
        itemImg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
        itemLabel.setText(item.getTitle());
        itemPrice.setText(item.getPrice());
    }
}
