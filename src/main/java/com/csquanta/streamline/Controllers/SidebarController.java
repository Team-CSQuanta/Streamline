package com.csquanta.streamline.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SidebarController {
    @FXML
    private ImageView habitImg;

    @FXML
    private ImageView leaderboardImg;

    @FXML
    private ImageView overviewImg;

    @FXML
    private ImageView settingsImg;

    @FXML
    private ImageView shopImg;

    @FXML
    private ImageView signImg;

    @FXML
    private ImageView taskImg;
    @FXML
    void overviewEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-dashboard-layout-24v2.png");
        overviewImg.setImage(new Image(stream));
    }

    @FXML
    void overviewExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-dashboard-layout-24.png");
        overviewImg.setImage(new Image(stream));
    }
    @FXML
    void taskEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-microsoft-to-do-app-24v2.png");
        taskImg.setImage(new Image(stream));
    }

    @FXML
    void taskExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-microsoft-to-do-app-24.png");
        taskImg.setImage(new Image(stream));
    }
    @FXML
    void habitEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-combo-chart-24v2.png");
        habitImg.setImage(new Image(stream));
    }

    @FXML
    void habitExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-combo-chart-24.png");
        habitImg.setImage(new Image(stream));
    }
    @FXML
    void leaderboardEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-leaderboard-24v2.png");
        leaderboardImg.setImage(new Image(stream));
    }

    @FXML
    void leaderboardExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-leaderboard-24.png");
        leaderboardImg.setImage(new Image(stream));
    }

    @FXML
    void shopEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-settings-24v2.png");
        shopImg.setImage(new Image(stream));
    }

    @FXML
    void shopExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-settings-24.png");
        shopImg.setImage(new Image(stream));
    }
    @FXML
    void signoutEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-sign-out-24v2.png");
        signImg.setImage(new Image(stream));
    }

    @FXML
    void signoutExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-sign-out-24.png");
        signImg.setImage(new Image(stream));
    }
    @FXML
    void settingsEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-settings-24v2.png");
        settingsImg.setImage(new Image(stream));
    }
    @FXML
    void settingsExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-settings-24.png");
        settingsImg.setImage(new Image(stream));
    }

    @FXML
    void onHabitTrackerBtnClicked(MouseEvent event) {

    }

    @FXML
    void onLeaderBoardBtnClicked(MouseEvent event) {

    }

    @FXML
    void onOverviewBtnClicked(MouseEvent event) {

    }

    @FXML
    void onSettingsBtnClicked(MouseEvent event) {

    }

    @FXML
    void onSignOutBtnclicked(MouseEvent event) {

    }

    @FXML
    void tasksBtnClicked(MouseEvent event) {

    }

}

