package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GotPointsController {

    @FXML
    private Label rewardAward;

    public String getRewardAward() {
        return rewardAward.getText();
    }
    public void setRewardAward(String rewardAward) {
        this.rewardAward.setText(rewardAward);
    }
}
