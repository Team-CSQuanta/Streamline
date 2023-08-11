package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Item;
import com.csquanta.streamline.Models.Shop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class ShopController implements Initializable {
    private static final Shop shop = new Shop();

    public static Shop getShop() {
        return shop;
    }

    @FXML
    private VBox BgContainerVbox;

    @FXML
    private VBox ItemBlockContainer;

    @FXML
    private ScrollPane ItemBlockScrollPane;

    @FXML
    private VBox ItemBlockVBox;

    @FXML
    private SplitPane ShopSpiltPane;

    @FXML
    private Label bgLabel;

    @FXML
    private ScrollPane bgScrollPane;

    @FXML
    private VBox bgTitleVBox;

    @FXML
    private Label bgUnderLabeltxt;

    @FXML
    private VBox bgVBox;

    @FXML
    private VBox shopContainerVbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox vbox1, vbox2;
        try {
            vbox1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/AvatarAccessory.fxml")));
            vbox2 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/PetRelatedAccessory.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Adding backgrounds
        TreeSet<Item> backgroundsInShop = shop.getBackgroundsList();
        shop.firstInitializeBackgrounds();
        for(Item bg: backgroundsInShop){
            FXMLScene fxmlScene;
            try {
                fxmlScene = FXMLScene.load("/Fxml/AvatarBackgrounds.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AvatarBackgrounds avatarBackgrounds = (AvatarBackgrounds) fxmlScene.controller;
            avatarBackgrounds.setBGData(bg);
            bgVBox.getChildren().add(fxmlScene.root);
        }

        ItemBlockVBox.getChildren().addAll(vbox1, vbox2);
    }

}
