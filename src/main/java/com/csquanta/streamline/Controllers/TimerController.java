package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.CountDown;
import com.csquanta.streamline.PomodoroClock;
import com.csquanta.streamline.TimeMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TimerController {
    @FXML private VBox container;
    @FXML private Label clockLabel;
    @FXML private ProgressBar clockProgressBar;
    @FXML private Button toggleBtn;
    @FXML private Button pomodoroBtn;
    @FXML private Button shortBreakBtn;
    @FXML private Button longBreakBtn;

    private CountDown countdown;
    private PomodoroClock clock;
    private Map<Button, TimeMode> buttonToMode;

    private VBox tempContainerOne;
    private VBox tempContainerTwo;

    public VBox getTempContainerOne() {
        return tempContainerOne;
    }

    public void setTempContainerOne(VBox tempContainerOne) {
        this.tempContainerOne = tempContainerOne;
    }

    public VBox getTempContainerTwo() {
        return tempContainerTwo;
    }

    public void setTempContainerTwo(VBox tempContainerTwo) {
        this.tempContainerTwo = tempContainerTwo;
    }

    @FXML
    public void initialize() {
        clock = new PomodoroClock(
                this, clockLabel, clockProgressBar, TimeMode.POMODORO);
        countdown = new CountDown(TimeMode.POMODORO, clock);
//        initializeButtonToMode();
    }
//
//    private void initializeButtonToMode() {
//        buttonToMode = new HashMap<>();
//        buttonToMode.put(pomodoroBtn, TimeMode.POMODORO);
//        buttonToMode.put(shortBreakBtn, TimeMode.BREAK);
//        buttonToMode.put(longBreakBtn, TimeMode.BREAK);
//    }

    public void toggleBtnClicked() {
        if (countdown.isRunning())
            stop();
        else
            activate();
    }

    private void stop() {
        countdown.stop();
        updateToggleBtn("RESUME");
    }

    private void updateToggleBtn(String text) {
        toggleBtn.setText(text);
    }

    private void activate() {
        if (countdown.isTimeUp())
            reset();
        start();
    }

    private void reset() {
        removeTimeIsUpStyles();
        countdown.reset();
    }

    private void removeTimeIsUpStyles() {
        container.getStyleClass().remove("time-is-up-background");
        toggleBtn.getStyleClass().remove("time-is-up-color");
    }

    private void start() {
        countdown.start();
        updateToggleBtn("STOP");
    }

    public void modeBtnClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        TimeMode mode = buttonToMode.get(button);
        changeMode(mode);
        highlightModeButton(button);
    }

    private void changeMode(TimeMode mode) {
        countdown.setMode(mode);
        clock.setMode(mode);
        removeTimeIsUpStyles();
        start();
    }

    private void highlightModeButton(Button button) {
        removeModeButtonHighlighting();
        button.getStyleClass().add("highlight-btn");
    }

    private void removeModeButtonHighlighting() {
        pomodoroBtn.getStyleClass().remove("highlight-btn");
        shortBreakBtn.getStyleClass().remove("highlight-btn");
        longBreakBtn.getStyleClass().remove("highlight-btn");
    }

    public void timeIsUp() {

        addTimeIsUpStyles();
        playSound();
        updateToggleBtn("RESET");
    }

    private void addTimeIsUpStyles() {
        System.out.println(tempContainerOne);
        System.out.println(tempContainerTwo);
        tempContainerTwo.getChildren().clear();
        tempContainerTwo.getChildren().setAll(tempContainerOne);
    }

    private void playSound() {
        Media sound = new Media(this.getClass().getResource("/Sounds/sound.wav").toString());
        MediaPlayer player = new MediaPlayer(sound);

        player.play();
    }


}