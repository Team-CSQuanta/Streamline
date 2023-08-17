package com.csquanta.streamline.Controllers;

import animatefx.animation.FadeInUp;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import static java.util.Objects.requireNonNull;

public class SidebarController implements Initializable {
    public final static ModalPane modalPaneForSignOut = new ModalPane();

    @FXML
    private ImageView challengeImg;

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
    void shopEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-shop-24v2.png");
        shopImg.setImage(new Image(stream));
    }

    @FXML
    void shopExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-shop-24.png");
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
    void challengesEntered(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-challenge-24v2.png");
        challengeImg.setImage(new Image(stream));
    }

    @FXML
    void challengesExited(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/Images/icons8-challenge-24.png");
        challengeImg.setImage(new Image(stream));
    }

    @FXML
    void onChallengesBtnClicked(MouseEvent event) throws IOException {
        App.newLoad();
        VBox challengePage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/Challenge.fxml")));
        StackPane.setAlignment(challengePage, Pos.BOTTOM_CENTER);
        App.root.getChildren().add(challengePage);
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(challengePage);
        zoomIn.setSpeed(3);
        zoomIn.play();
    }

    @FXML
    void onOverviewBtnClicked(MouseEvent event) {


    }

    @FXML
    void onSettingsBtnClicked(MouseEvent event) {

    }

    @FXML
    void onSignOutBtnclicked(MouseEvent event) throws IOException {
        VBox signout = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/SignOutWarningPage.fxml")));
        HeaderController.modalPaneForHeader.hide(true);
        modalPaneForSignOut.setAlignment(Pos.CENTER);
        modalPaneForSignOut.show(signout);
        Pulse pulse = new Pulse();
        pulse.setNode(signout);
        pulse.setSpeed(3);
        pulse.play();

    }

    @FXML
    void tasksBtnClicked(MouseEvent event) throws IOException {
        App.newLoad();
        VBox taskPage = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/ToDoListGridPane.fxml")));
        StackPane.setAlignment(taskPage, Pos.BOTTOM_CENTER);
        App.root.getChildren().add(taskPage);
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(taskPage);
        zoomIn.setSpeed(3);
        zoomIn.play();
    }
    @FXML
    void shopBtnClicked(MouseEvent event) throws IOException {
        App.newLoad();
        VBox shop = FXMLLoader.load(requireNonNull(getClass().getResource("/Fxml/Shop.fxml")));
        StackPane.setAlignment(shop, Pos.BOTTOM_CENTER);
        App.root.getChildren().add(shop);
        FadeInUp fadeInUp = new FadeInUp();
        ZoomIn zoomIn = new ZoomIn();
        zoomIn.setNode(shop);
        zoomIn.setSpeed(3);
        zoomIn.play();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

