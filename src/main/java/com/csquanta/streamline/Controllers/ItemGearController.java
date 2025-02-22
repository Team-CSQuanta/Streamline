package com.csquanta.streamline.Controllers;

import animatefx.animation.JackInTheBox;
import animatefx.animation.Shake;
import com.csquanta.streamline.Models.Item;
import com.csquanta.streamline.Models.StaticUserInformation;
import com.csquanta.streamline.Models.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

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
    private ImageView itemImage;

    @FXML
    private Label itemLabel;

    @FXML
    private Label itemPrice;
    private String itemType;
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

    @FXML
    void buyBtnClicked(ActionEvent event){
        if (event.getSource() instanceof Button buyBtn) {
            Label priceLabel = (Label) buyBtn.getParent().getParent().getParent().lookup("#itemPrice");
            Label itemName = (Label) buyBtn.getParent().getParent().getParent().lookup("#itemLabel");
            if(StaticUserInformation.totalGoldCoins >= Double.parseDouble(priceLabel.getText())){
                try {
                    FXMLScene fxmlScene = FXMLScene.load("/Fxml/ItemGearPurchasedSuccessFully.fxml");
                    VBox successMsg = (VBox) fxmlScene.root;
                    ItemPurchasedSuccessFullyController itemPurchasedSuccessFullyController = (ItemPurchasedSuccessFullyController) fxmlScene.controller;
                    itemPurchasedSuccessFullyController.setItemLabel(itemLabel);
                    itemPurchasedSuccessFullyController.setItemPurchasedImg(itemImage);
                    itemContainer.setStyle("-fx-background-color: transparent;");
                    itemContainer.getChildren().setAll(successMsg);
                    UserInformation.userInfo.deductGoldCoins(Double.parseDouble(priceLabel.getText()));
                    JackInTheBox flip = new JackInTheBox(successMsg);
                    flip.setSpeed(1);
                    flip.play();
                    soundPlayer("/Sounds/mixkit-clinking-coins-1993.wav");
                    String imagePath = (String) itemImage.getUserData();
                    if(itemType.equals("Armor")){
                        Item itemNeedToAdd = new Item(imagePath, itemName.getText(), priceLabel.getText(), "Armor");
                        ShopController.getShop().addArmorToBoughtList(itemNeedToAdd);
                    }else if(itemType.equals("Head Wear")){
                        Item itemNeedToAdd = new Item(imagePath, itemName.getText(), itemPrice.getText(), "Head Wear");
                        ShopController.getShop().addHeadWearToBoughtList(itemNeedToAdd);
                    }


                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                Shake shake = new Shake(itemContainer);
                shake.setSpeed(2);
                shake.play();
            }
        }

    }
    public void setData(Item item){
        String imagePath = item.getImgSrc();
        itemImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
        itemImage.setUserData(imagePath);
        itemLabel.setText(item.getTitle());
        itemPrice.setText(item.getPrice());
        itemType = item.getItemType();
    }
    public void soundPlayer(String soundPath){
//        Media sound = new Media(this.getClass().getResource(soundPath).toString());
//        MediaPlayer player = new MediaPlayer(sound);
//        player.play();

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBuyButton.setOnAction(this::buyBtnClicked);
    }
}
