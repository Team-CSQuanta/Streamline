package com.csquanta.streamline;
import com.csquanta.streamline.Controllers.TaskBlockController;
import com.csquanta.streamline.Controllers.TimerController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
public class PomodoroClock implements CountDownObserver {
    private final TaskBlockController controller;
    private final Label clockLabel;
    private final ProgressBar progressBar;
    private TimeMode mode;


    public PomodoroClock(TaskBlockController controller,
                         Label clockLabel,
                         ProgressBar progressBar,
                         TimeMode mode) {
        this.controller = controller;
        this.clockLabel = clockLabel;
        this.progressBar = progressBar;
        setMode(mode);
    }

    @Override
    public void update(int seconds) {
        Platform.runLater(() -> {
            clockLabel.setText(secondsToString(seconds));
            updateProgressBar(seconds);
        });
    }

    @Override
    public void timeIsUp() {
     Platform.runLater(controller::timeIsUp);
   }

    private String secondsToString(int seconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void updateProgressBar(int seconds) {
        progressBar.setProgress((float) seconds / mode.getSeconds());
    }

    public void setMode(TimeMode mode) {
        this.mode = mode;
        clockLabel.setText(secondsToString(mode.getSeconds()));
        progressBar.setProgress(1);
    }
}
