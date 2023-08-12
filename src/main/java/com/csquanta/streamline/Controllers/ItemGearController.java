package com.csquanta.streamline.Controllers;

import animatefx.animation.*;
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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class ItemGearController implements Initializable {
    ProfileEditController profileEditController= new ProfileEditController();
    @FXML
    private Button itemBuyButton;

    @FXML
    private VBox itemContainer;

    @FXML
    private ImageView armor;

    @FXML
    private Label itemLabel;

    @FXML
    private Label itemPrice;

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
            if(StaticUserInformation.totalGoldCoins >= Double.parseDouble(priceLabel.getText())){
                try {
                    FXMLScene fxmlScene = FXMLScene.load("/Fxml/ItemGearPurchasedSuccessFully.fxml");

                    VBox successMsg = (VBox) fxmlScene.root;
                    ItemPurchasedSuccessFullyController itemPurchasedSuccessFullyController = (ItemPurchasedSuccessFullyController) fxmlScene.controller;
                    itemPurchasedSuccessFullyController.setItemLabel(itemLabel);
                    itemPurchasedSuccessFullyController.setItemPurchasedImg(armor);
                    itemContainer.setStyle("-fx-background-color: transparent;");
                    itemContainer.getChildren().setAll(successMsg);
                    UserInformation.userInfo.deductGoldCoins(Double.parseDouble(priceLabel.getText()));
                    JackInTheBox flip = new JackInTheBox(successMsg);
                    flip.setSpeed(1);
                    flip.play();

                    String imagePath = (String) armor.getUserData();
                    Item itemNeedToAdd = new Item(imagePath, null, null);
                    ShopController.getShop().addArmorToBuyedList(itemNeedToAdd);
//                    Image image=  new Image(requireNonNull(getClass().getResourceAsStream(imagePath)));
//                    ImageView purchasedItemImageView = new ImageView(image);
//                    profileEditController.addPurchasedItemToAvatar(purchasedItemImageView, "gridPaneArmor");

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
        armor.setImage(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
        armor.setUserData(imagePath);
        itemLabel.setText(item.getTitle());
        itemPrice.setText(item.getPrice());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemBuyButton.setOnAction(this::buyBtnClicked);
    }
}
