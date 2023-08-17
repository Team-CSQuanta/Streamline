package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.Models.EvilMonsters;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class MonsterItemController {

    @FXML
    private Label MonsterName;

    @FXML
    private Label healthPointsText;

    @FXML
    private ProgressBar healthProgressBar;

    @FXML
    private ImageView monsterImage;

    @FXML
    private Label prizeCoins;

    @FXML
    private ProgressBar strikeProgressBar;

    @FXML
    private Label strikeText;
    public void setMonsterData(EvilMonsters monster){
        this.MonsterName.setText(monster.getName());
        this.monsterImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(monster.getMonsterImagePath()))));
        this.prizeCoins.setText(String.valueOf(monster.getPrizePoints()));
        if(monster.getHealth() == 3000){
            healthProgressBar.setProgress(1.0);
            healthPointsText.setText(String.valueOf(monster.getHealth()) + " Hp");
            strikeProgressBar.setProgress(.833);
            strikeText.setText(String.valueOf(monster.getDamagePerAttack()) + " Hp");
        }
        else if(monster.getHealth() == 2000){
            healthProgressBar.setProgress(.5);
            healthPointsText.setText(String.valueOf(monster.getHealth()) + " Hp");
            strikeProgressBar.setProgress(.5);
            strikeText.setText(String.valueOf(monster.getDamagePerAttack()) + " Hp");
        }
        else if(monster.getHealth() == 1000){
            healthProgressBar.setProgress(.3);
            healthPointsText.setText(String.valueOf(monster.getHealth()) + " Hp");
            strikeProgressBar.setProgress(0.167);
            strikeText.setText(String.valueOf(monster.getDamagePerAttack()) + " Hp");
        }
    }
}
