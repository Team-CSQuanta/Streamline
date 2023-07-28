package com.csquanta.streamline.Controllers;

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

public class HeaderController {
    public static final ModalPane modalPaneForHeader = new ModalPane();
    @FXML
    private ImageView inbox;

    @FXML
    private Label logo;

    @FXML
    private ImageView notification;

    @FXML
    private ImageView sideMenuLoader;

    @FXML
    private ImageView user;
    @FXML
    void takeBreakClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxml/TakeBreak.fxml")));
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(loader.load());
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
    void notificationClicked(MouseEvent event) {

    }

    @FXML
    void userIconClicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxml/Profile.fxml")));
        modalPaneForHeader.setAlignment(Pos.CENTER);
        modalPaneForHeader.show(loader.load());
    }

}
