package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.Armor;
import com.csquanta.streamline.Models.ArmorComp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Objects;
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
        while(iterator.hasNext()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/ItemGear.fxml"));
            ItemGearController itemGearController = fxmlLoader.getController();
            itemGearController.setItemPrice(new Label(iterator.next().getPrice()));
//            try {
////                armorTabHbox.getChildren().add(fxmlLoader.load());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
    private TreeSet<Armor> getArmorData(){
        TreeSet<Armor> armorsList = new TreeSet<>(new ArmorComp());
        armorsList.add(new Armor("src/main/resources/Images/gear/armor/Healer/healer_1.png", "Healer V1", "100"));
        armorsList.add(new Armor("src/main/resources/Images/gear/armor/Healer/healer_2.png", "Healer V2", "200"));
        armorsList.add(new Armor("src/main/resources/Images/gear/armor/Healer/healer_3.png", "Healer V3", "300"));
        armorsList.add(new Armor("src/main/resources/Images/gear/armor/Healer/healer_4.png", "Healer V4", "400"));
        armorsList.add(new Armor("src/main/resources/Images/gear/armor/Healer/healer_5.png", "Healer V5", "500"));
        return armorsList;
    }
}

