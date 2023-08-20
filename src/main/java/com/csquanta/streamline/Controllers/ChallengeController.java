package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeInUp;
import animatefx.animation.Pulse;
import com.csquanta.streamline.Models.EvilMonsters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.TreeSet;

import static java.util.Objects.requireNonNull;

public class ChallengeController implements Initializable {
    VBox noChallenge;
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

    @FXML
    void takeChallenge(ActionEvent event) throws IOException {
        VBox challengeCreatePage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ChallengeCreator.fxml")));
        CreateANewTaskController.modalPaneForTaskCreator.show(challengeCreatePage);
        Pulse pulse = new Pulse(challengeCreatePage);
        pulse.play();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NotHavingAnyChallenges(bottomVbox);
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
    }

    public void NotHavingAnyChallenges(VBox bottomVbox){
        if(bottomVbox.getChildren().size() == 0){
            try {
                noChallenge = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/NotHavingAnyChallenge.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bottomVbox.getChildren().add(noChallenge);
        }
    }
}
