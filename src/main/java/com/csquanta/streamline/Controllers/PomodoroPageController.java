package com.csquanta.streamline.Controllers;

import atlantafx.base.controls.ModalPane;
import com.csquanta.streamline.Models.MyTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class PomodoroPageController implements Initializable {
    public static ModalPane modalPaneForPomodoroPage = new ModalPane();
    @FXML
    private Label minutesLabel;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label secondsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MyTimer timer = new MyTimer(2, 60, minutesLabel, secondsLabel);
        timer.t.setDaemon(true);
        timer.t.start();


    }

}


