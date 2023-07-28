package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
