package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class MonsterInChallengeController {

    @FXML
    private ProgressBar monsterHealthRemaining;

    @FXML
    private ImageView monsterImage;

    @FXML
    private Label monsterName;

    @FXML
    private ProgressBar strikeProgressBar;

    @FXML
    private HBox topHbox;

    @FXML
    private HBox totalUsersHealthDamagedByTheMonster;

    public ProgressBar getMonsterHealthRemaining() {
        return monsterHealthRemaining;
    }

    public void setMonsterHealthRemaining(double monsterHealthRemaining) {
        this.monsterHealthRemaining.setProgress(monsterHealthRemaining);
    }

    public ImageView getMonsterImage() {
        return monsterImage;
    }

    public void setMonsterImage(Image monsterImage) {
        this.monsterImage.setImage(monsterImage);
    }

    public Label getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName.setText(monsterName);
    }

    public ProgressBar getStrikeProgressBar() {
        return strikeProgressBar;
    }

    public void setStrikeProgressBar(double strikeProgressBar) {
        this.strikeProgressBar.setProgress(strikeProgressBar);
    }

    public HBox getTopHbox() {
        return topHbox;
    }

    public void setTopHbox(HBox topHbox) {
        this.topHbox = topHbox;
    }

    public HBox getTotalUsersHealthDamagedByTheMonster() {
        return totalUsersHealthDamagedByTheMonster;
    }

    public void setTotalUsersHealthDamagedByTheMonster(HBox totalUsersHealthDamagedByTheMonster) {
        this.totalUsersHealthDamagedByTheMonster = totalUsersHealthDamagedByTheMonster;
    }
    @FXML
    void onMonsterImageDragDetected(MouseEvent event) {
//        ItemGearController controller = new ItemGearController();
//        controller.soundPlayer("/Sounds/mixkit-giant-monster-roar-1972.wav");
    }
}
