package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Armor;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class ShopBlockController implements Initializable {

    @FXML
    private ScrollPane ArmorTabScrollPane;

    @FXML
    private Label ItemBlockTitle;

    @FXML
    private HBox armorTabHbox;


    @FXML
    private ScrollPane headWearScrollPane;

    @FXML
    private HBox headWearTabHBox;

    @FXML
    private Tab itemBlockTab;

    @FXML
    private Tab itemBlockTab2;

    @FXML
    private TabPane itemBlockTabPane;

    @FXML
    private VBox mainVbox;
    @FXML
    void armorTabSelect(Event event) {
        if(itemBlockTab.isSelected()) {

            TreeSet<Armor> armorsInShop = ShopController.getShop().getArmorsList();
            ShopController.getShop().firstInitializeArmor();
            Iterator<Armor> iterator = armorsInShop.iterator();
            FXMLScene fxmlScene;
            while (iterator.hasNext()) {
                try {
                    fxmlScene = FXMLScene.load("/Fxml/ItemGear.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ItemGearController itemGearController = (ItemGearController) fxmlScene.controller;
                itemGearController.setData(iterator.next());
                armorTabHbox.getChildren().add(fxmlScene.root);
            }
        }
    }

    @FXML
    void headWearTabSelect(Event event) {
        if(itemBlockTab2.isSelected())
            System.out.println("head wear tab selected");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        }
    }


