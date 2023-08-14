package com.csquanta.streamline.Controllers;


import com.csquanta.streamline.CountDown;
import com.csquanta.streamline.Models.Task;
import com.csquanta.streamline.PomodoroClock;
import com.csquanta.streamline.TimeMode;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.HashMap;
import java.util.Map;

import static com.csquanta.streamline.Models.Task.taskObject;

public class TimerController {
    private int numPomodoroSessions;
    @FXML private VBox container;
    @FXML private Label clockLabel;
    @FXML private ProgressBar clockProgressBar;
    @FXML private Button toggleBtn;
    @FXML
    private Label sessionCount;

    @FXML private Button pomodoroBtn;
    Task task = new Task();
    private CountDown countdown;
    private PomodoroClock clock;
    private int pomodoroSessions;
    private final int maxLoopsCounts = pomodoroSessions;

    public int currentAutoLoop =1;
    private Map<Button, TimeMode> buttonToMode;

    public void initialize() {
        clock = new PomodoroClock(
                this, clockLabel, clockProgressBar, TimeMode.POMODORO);
        countdown = new CountDown(TimeMode.POMODORO, clock);
        initializeButtonToMode();
        System.out.println("max"+maxLoopsCounts);
    }

    private void initializeButtonToMode() {
        buttonToMode = new HashMap<>();
        buttonToMode.put(pomodoroBtn, TimeMode.POMODORO);

    }

    public void toggleBtnClicked() {
        if (countdown.isRunning())
            stop();     // stops the counter if it is already running
        else
            activate(); // activate the counter if it is not running
    }

    private void stop() {
        countdown.stop();
//        updateToggleBtn("Resume");
    }

    private void updateToggleBtn(String text) {
        toggleBtn.setText(text);
    }

    private void activate() {
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
        System.out.println("max"+maxLoopsCounts);
        countdown.start();
        updateToggleBtn("Stop");
    }

    public void timeIsUp() {
        addTimeIsUpStyles();
        playSound();

        if ( currentAutoLoop < maxLoopsCounts) {
            reset();
            currentAutoLoop++;
            start();
           sessionCount.setText(String.valueOf(currentAutoLoop));
            System.out.println(currentAutoLoop);


        } else {
            stop();
            updateToggleBtn("Reset");
        }
    }

    private void addTimeIsUpStyles() {
        container.getStyleClass().add("time-is-up-background");
        toggleBtn.getStyleClass().add("time-is-up-color");
    }

    private void playSound() {
       Media sound = new Media(this.getClass().getResource("/Sounds/sound.wav").toString());
      MediaPlayer player = new MediaPlayer(sound);

        player.play();
    }


}
