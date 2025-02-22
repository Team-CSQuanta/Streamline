package com.csquanta.streamline.Controllers;

import animatefx.animation.Pulse;
import com.csquanta.streamline.Models.ChallengeUI;
import com.csquanta.streamline.Models.EvilMonsters;
import com.csquanta.streamline.Models.UserInformation;
import com.csquanta.streamline.Networking.NetworkUtil;
import com.csquanta.streamline.Networking.ReadThreadClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;

import static com.csquanta.streamline.Controllers.HeaderController.modalPaneForHeader;
import static com.csquanta.streamline.Models.UserInformation.userInfo;
import static java.util.Objects.requireNonNull;

public class ChallengeController implements Initializable {

    @FXML
    private VBox ItemBlockContainer;

    @FXML
    private VBox MonsterContainerVbox;

    @FXML
    private Label MonsterLabel;

    @FXML
    private ScrollPane MonsterScrollPane;

    @FXML
    private VBox MonsterTitleVBox;

    @FXML
    private Label MonsterUnderLabeltxt;

    @FXML
    private VBox MonsterVBox;

    @FXML
    private SplitPane ShopSpiltPane;

    @FXML
    private VBox bottomVbox;
    ChallengeCreatorController controller;

    public VBox getBottomVbox() {
        return bottomVbox;
    }

    public void setBottomVbox(VBox bottomVbox) {
        this.bottomVbox = bottomVbox;
    }

    @FXML
    private VBox shopContainerVbox;

    @FXML
    private Button takeChallengeBtn;

    @FXML
    private HBox topHbox;
    public static NetworkUtil networkUtil;


    public HBox getTopHbox() {
        return topHbox;
    }

    public void setTopHbox(HBox topHbox) {
        this.topHbox = topHbox;
    }

    @FXML
    void takeChallenge(ActionEvent event) throws IOException {

        FXMLScene fxmlScene = FXMLScene.load("/Fxml/ChallengeCreator.fxml");
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(fxmlScene.root);
         controller = (ChallengeCreatorController) fxmlScene.controller;
        Pulse pulse = new Pulse();
        pulse.setNode(modalPaneForHeader);
        pulse.play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        challengePageStatus(bottomVbox);
        TreeSet<EvilMonsters> monsters = EvilMonsters.evilMonstersStaticObject.getEvilMonstersList();
        Iterator<EvilMonsters> iterator = monsters.iterator();
        FXMLScene fxmlScene;
        while (iterator.hasNext()) {
            try {
                fxmlScene = FXMLScene.load("/Fxml/MonstersItem.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MonsterItemController monsterItemController = (MonsterItemController) fxmlScene.controller;
            monsterItemController.setMonsterData(iterator.next());
            MonsterVBox.getChildren().add(fxmlScene.root);
        }


        // Initialize the socket connection here
        try {
            String clientEmail = userInfo.getEmail();
            networkUtil = new NetworkUtil("127.0.0.1", 8000);
            networkUtil.write(clientEmail);
            System.out.println(clientEmail);
            ReadThreadClient readThreadClient = new ReadThreadClient(networkUtil, clientEmail);
            readThreadClient.start();
        } catch (Exception e) {
            System.out.println("Error initializing socket connection: " + e);
        }
    }


    public void challengePageStatus(VBox bottomVbox){
        if(!bottomVbox.getChildren().isEmpty()){
            bottomVbox.getChildren().removeAll();
            if(!ChallengeUI.challengeUI.getChallengeMode() && !ChallengeUI.challengeUI.isPending()){
                try {
                    VBox noChallenge = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/NotHavingAnyChallenge.fxml")));
                    bottomVbox.getChildren().add(noChallenge);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if(ChallengeUI.challengeUI.isPending()){
                try {
                    FXMLScene challengeReqSent = FXMLScene.load("/Fxml/ChallengeRequestSent.fxml");
                    bottomVbox.getChildren().add(challengeReqSent.root);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else if(ChallengeUI.challengeUI.getChallengeMode()){
                System.out.println("In challenge mode");
            }
        }

    }



}
