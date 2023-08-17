package com.csquanta.streamline.Controllers;

import animatefx.animation.JackInTheBox;
import animatefx.animation.Shake;
import com.csquanta.streamline.Models.Item;
import com.csquanta.streamline.Models.StaticUserInformation;
import com.csquanta.streamline.Models.UserInformation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.Objects;

public class AvatarBackgroundsController {

    @FXML
    private HBox avatarBgMainContainer;

    @FXML
    private Button bgBuyBtn;

    @FXML
    private ImageView bgImage;

    @FXML
    private Label bgPriceTag;

    @FXML
    private Label bgTitle;
    private String itemType;
    @FXML
    void buyBtnClicked(ActionEvent event) {
        if (event.getSource() instanceof Button buyBtn) {
            Label priceLabel = (Label) buyBtn.getParent().getParent().getParent().lookup("#bgPriceTag");
            Label itemName = (Label) buyBtn.getParent().getParent().getParent().lookup("#bgTitle");
            if(StaticUserInformation.totalGoldCoins >= Double.parseDouble(priceLabel.getText())){
                try {
                    FXMLScene fxmlScene = FXMLScene.load("/Fxml/AvatarBackgroundsPurchasedSuccessFully.fxml");

                    HBox successMsg = (HBox) fxmlScene.root;
                    AvatarBackgroundsPurchasedController avatarBackgroundsPurchasedController = (AvatarBackgroundsPurchasedController) fxmlScene.controller;
                    avatarBackgroundsPurchasedController.setItemTitle(bgTitle);
                    avatarBackgroundsPurchasedController.setBGPurchasedImg(bgImage);
                    avatarBgMainContainer.setStyle("-fx-background-color: transparent; -fx-border-width: 0");
                    avatarBgMainContainer.setPadding(new Insets(0,0, 0, 0));
                    avatarBgMainContainer.getChildren().setAll(successMsg);
                    UserInformation.userInfo.deductGoldCoins(Double.parseDouble(priceLabel.getText()));
                    JackInTheBox flip = new JackInTheBox(successMsg);
                    flip.setSpeed(1);
                    flip.play();
                    soundPlayer("/Sounds/mixkit-clinking-coins-1993.wav");
                    String imagePath = (String) bgImage.getUserData();
                    if(itemType.equals("Backgrounds")){
                        Item itemNeedToAdd = new Item(imagePath, itemName.getText(), priceLabel.getText(), "Backgrounds");
                        ShopController.getShop().addBackgroundToBoughtList(itemNeedToAdd);
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                Shake shake = new Shake(avatarBgMainContainer);
                shake.setSpeed(2);
                shake.play();
            }
        }
    }
    public void setBGData(Item item){
        bgImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
        bgImage.setUserData(item.getImgSrc());
        bgTitle.setText(item.getTitle());
        bgPriceTag.setText(item.getPrice());
        itemType = item.getItemType();
    }
    public void soundPlayer(String soundPath){
//        Media sound = new Media(this.getClass().getResource(soundPath).toString());
//        MediaPlayer player = new MediaPlayer(sound);
//        player.play();

    }
}
