package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.BreakClock;
import com.csquanta.streamline.CountDown;
import com.csquanta.streamline.PomodoroClock;
import com.csquanta.streamline.TimeMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class BreakController {
    @FXML
    private VBox container;
    @FXML private Label clockLabel;
    @FXML private ProgressBar clockProgressBar;
    @FXML private Button toggleBtn;
    @FXML private Button pomodoroBtn;
    @FXML private Button shortBreakBtn;
    @FXML private Button longBreakBtn;
    @FXML private ImageView icon;

    private CountDown countdown;
    private BreakClock clock;
    private Map<Button, TimeMode> buttonToMode;

    public void initialize() {
        clock = new BreakClock(
                this, clockLabel, clockProgressBar, TimeMode.POMODORO);
        countdown = new CountDown(TimeMode.BREAK, clock);
        initializeButtonToMode();
    }

    private void initializeButtonToMode() {
        buttonToMode = new HashMap<>();
        buttonToMode.put(pomodoroBtn, TimeMode.BREAK);

    }

    public void toggleBtnClicked() {
        if (countdown.isRunning())
            stop();     // stops the counter if it is already running
        else
            activate(); // activate the counter if it is not running
    }

    private void stop() {
        countdown.stop();
        updateToggleBtn("Resume");
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
        updateToggleBtn("Stop");
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
    }

    public void timeIsUp() {
        addTimeIsUpStyles();
        playSound();
        updateToggleBtn("Reset");
    }

    private void addTimeIsUpStyles() {
        container.getStyleClass().add("time-is-up-background");
        toggleBtn.getStyleClass().add("time-is-up-color");
    }

    private void playSound() {
//        Media sound = new Media(this.getClass().getResource("/Sounds/sound.wav").toString());
//        MediaPlayer player = new MediaPlayer(sound);
//
//        player.play();
    }
}
