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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemControllerEFP implements Initializable {

    @FXML
    private Button itemBuyBtn;

    @FXML
    private VBox itemEFPMainContainer;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label itemPriceTag;

    @FXML
    private Label itemTitle;
    private String itemType;

    public ImageView getItemImage() {
        return itemImage;
    }

    public Button getItemBuyBtn() {
        return itemBuyBtn;
    }

    public void setItemBuyBtn(Button itemBuyBtn) {
        this.itemBuyBtn = itemBuyBtn;
    }

    public VBox getItemEFPMainContainer() {
        return itemEFPMainContainer;
    }

    public void setItemEFPMainContainer(VBox itemEFPMainContainer) {
        this.itemEFPMainContainer = itemEFPMainContainer;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemImage(ImageView itemImage) {
        this.itemImage = itemImage;
    }

    public Label getItemPriceTag() {
        return itemPriceTag;
    }

    public void setItemPriceTag(Label itemPriceTag) {
        this.itemPriceTag = itemPriceTag;
    }

    public Label getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(Label itemTitle) {
        this.itemTitle = itemTitle;
    }
    private String petPath;

    public void setPetPath(String petPath) {
        this.petPath = petPath;
    }

    public void setPetData(Item item){
        String imagePath = item.getImgSrc();
        itemImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toExternalForm()));
        itemImage.setUserData(imagePath);
        itemTitle.setText(item.getTitle());
        itemPriceTag.setText(item.getPrice());
        itemType = item.getItemType();
    }
    @FXML
    void buyBtnClicked(ActionEvent event) {
        if (event.getSource() instanceof Button buyBtn) {
            Label priceLabel = (Label) buyBtn.getParent().getParent().getParent().lookup("#itemPriceTag");
            Label itemName = (Label) buyBtn.getParent().getParent().getParent().lookup("#itemTitle");
            if(StaticUserInformation.totalGoldCoins >= Double.parseDouble(priceLabel.getText())){
                try {
                    FXMLScene fxmlScene = FXMLScene.load("/Fxml/ItemGearPurchasedSuccessFully.fxml");

                    VBox successMsg = (VBox) fxmlScene.root;
                    ItemPurchasedSuccessFullyController itemPurchasedSuccessFullyController = (ItemPurchasedSuccessFullyController) fxmlScene.controller;
                    itemPurchasedSuccessFullyController.setItemLabel(itemTitle);
                    itemPurchasedSuccessFullyController.setItemPurchasedImg(itemImage);
                    itemEFPMainContainer.setStyle("-fx-background-color: transparent;");
                    itemEFPMainContainer.getChildren().setAll(successMsg);
                    UserInformation.userInfo.deductGoldCoins(Double.parseDouble(priceLabel.getText()));
                    JackInTheBox flip = new JackInTheBox(successMsg);
                    flip.setSpeed(1);
                    flip.play();
                    soundPlayer("/Sounds/mixkit-clinking-coins-1993.wav");
                    String imagePath = (String) itemImage.getUserData();
                    if(itemType.equals("Pet")){
                        Item itemNeedToAdd = new Item(imagePath, itemName.getText(), priceLabel.getText(), "Armor");
                        ShopController.getShop().addPetToBoughtList(itemNeedToAdd);
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }else{
                Shake shake = new Shake(itemEFPMainContainer);
                shake.setSpeed(2);
                shake.play();
            }
        }
    }
    public void setCustomizePetData(Image image){
        itemImage.setImage(image);
    }
    public void soundPlayer(String soundPath){
//        Media sound = new Media(this.getClass().getResource(soundPath).toString());
//        MediaPlayer player = new MediaPlayer(sound);
//        player.play();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
