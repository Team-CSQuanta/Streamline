package com.csquanta.streamline.Controllers;

import animatefx.animation.Flip;
import com.csquanta.streamline.Models.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemGearController implements Initializable {

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
        try {
            FXMLScene fxmlScene = FXMLScene.load("/Fxml/ItemGearPurchasedSuccessFully.fxml");

            VBox successMsg = (VBox) fxmlScene.root;
            ItemPurchasedSuccessFullyController itemPurchasedSuccessFullyController = (ItemPurchasedSuccessFullyController) fxmlScene.controller;
            itemPurchasedSuccessFullyController.setItemLabel(itemLabel);
            itemPurchasedSuccessFullyController.setItemPurchasedImg(itemImg);
            itemContainer.setStyle("-fx-background-color: transparent;");
            itemContainer.getChildren().setAll( successMsg);

            Flip flip = new Flip(successMsg);
            flip.setSpeed(2);
            flip.play();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void setData(Item item){
        itemImg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
        itemLabel.setText(item.getTitle());
        itemPrice.setText(item.getPrice());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBuyButton.setOnAction(this::buyBtnClicked);
    }
}
