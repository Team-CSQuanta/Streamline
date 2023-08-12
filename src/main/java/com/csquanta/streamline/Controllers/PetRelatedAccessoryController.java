package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class PetRelatedAccessoryController implements Initializable {

    @FXML
    private ScrollPane ArmorTabScrollPane;

    @FXML
    private HBox EggHatchingPotionHbox;

    @FXML
    private Label ItemBlockTitle;

    @FXML
    private HBox PetEggsHbox;

    @FXML
    private HBox PetFoodsHbox;

    @FXML
    private HBox PetsHbox;

    @FXML
    private Tab itemBlockTab1;

    @FXML
    private Tab itemBlockTab1ScrollPane;

    @FXML
    private ScrollPane itemBlockTab2ScrollPane;

    @FXML
    private Tab itemBlockTab3;

    @FXML
    private ScrollPane itemBlockTab3ScrollPane;

    @FXML
    private Tab itemBlockTab4;

    @FXML
    private ScrollPane itemBlockTab4ScrollPane;

    @FXML
    private TabPane itemBlockTabPane;


    public void addItemToPetShop(TreeSet<Item> itemList, HBox tab, String itemType){
        Iterator<Item> iterator = itemList.iterator();
        FXMLScene fxmlScene;
        while (iterator.hasNext()) {
            try {
                fxmlScene = FXMLScene.load("/Fxml/ItemEFP.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ItemControllerEFP itemControllerEFP = (ItemControllerEFP) fxmlScene.controller;
            itemControllerEFP.setPetData(iterator.next());
            tab.getChildren().add(fxmlScene.root);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeSet<Item> petsInShop = ShopController.getShop().getPetsList();
        ShopController.getShop().firstPetInitialize();
        addItemToPetShop(petsInShop, PetsHbox, "Pet");
    }
}
