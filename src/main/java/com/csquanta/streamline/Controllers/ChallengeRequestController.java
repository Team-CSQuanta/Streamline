package com.csquanta.streamline.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ChallengeRequestController {

    @FXML
    public Label ChallengeType;

    @FXML
    public Label PomoSession;

    @FXML
    public Label TaskTitle;

    @FXML
    private Button acceptBtn;

    @FXML
    public ImageView avatarArmor;

    @FXML
    public ImageView avatarBody;

    @FXML
    public ImageView avatarHair;

    @FXML
    public ImageView avatarHead;

    @FXML
    public ImageView avatarPet;

    @FXML
    public ImageView headGear;

    @FXML
    public ImageView image_bg;

    @FXML
    private Button decline;


    @FXML
    public Label monsterName;

    @FXML
    private AnchorPane profileArea;

    @FXML
    public Label taskDescription;

    @FXML
    public Label taskTag;
    @FXML
    public Label SessionNo;
    @FXML
    public Label Ttag;



    @FXML
    void onAcceptBtnClicked(ActionEvent event) {


    }

    @FXML
    void onDeclineBtnClicked(ActionEvent event) {

    }

}