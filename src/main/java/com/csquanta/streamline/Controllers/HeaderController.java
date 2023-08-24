package com.csquanta.streamline.Controllers;

import animatefx.animation.Pulse;
import atlantafx.base.controls.ModalPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;
import com.csquanta.streamline.Controllers.FXMLScene;

public class HeaderController {
    public static final ModalPane modalPaneForHeader = new ModalPane();
    @FXML
    private ImageView inbox;

    @FXML
    private Label logo;

    @FXML
    private ImageView sideMenuLoader;

    @FXML
    private ImageView user;
    static ProfileViewController controller;

    public static ProfileViewController getController() {
        return controller;
    }

    @FXML
    void takeBreakClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxml/TakeBreak.fxml")));
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(loader.load());
        Pulse pulse = new Pulse();
        pulse.setNode(modalPaneForHeader);
        pulse.play();

    }
    @FXML
    void inboxClicked(MouseEvent event) {

    }

    @FXML
    void loadSideMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxml/Sidebar.fxml")));
        modalPaneForHeader.setAlignment(Pos.CENTER_LEFT);
        modalPaneForHeader.usePredefinedTransitionFactories(Side.LEFT);
        modalPaneForHeader.show(loader.load());

    }

    @FXML
    void userIconClicked(MouseEvent event) throws IOException {
        FXMLScene fxmlScene =FXMLScene.load("/Fxml/Profile.fxml");

        controller = (ProfileViewController) fxmlScene.controller;
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(fxmlScene.root);
        Pulse pulse = new Pulse();
        pulse.setNode(modalPaneForHeader);
        pulse.play();
    }

}
