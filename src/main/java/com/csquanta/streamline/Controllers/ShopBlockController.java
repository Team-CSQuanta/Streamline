package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Armor;
import com.csquanta.streamline.Models.ArmorComp;
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
    private final TreeSet<Armor> armors = getArmorData();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Iterator<Armor> iterator = armors.iterator();
        FXMLScene fxmlScene;
        while(iterator.hasNext()){
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
    private TreeSet<Armor> getArmorData(){
        TreeSet<Armor> armorsList = new TreeSet<>(new ArmorComp());
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_1.png", "Healer V1", "100"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_2.png", "Healer V2", "200"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_3.png", "Healer V3", "300"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_4.png", "Healer V4", "400"));
        armorsList.add(new Armor("/Images/gear/armor/Healer/healer_5.png", "Healer V5", "500"));
        return armorsList;
    }
}

