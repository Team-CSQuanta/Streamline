package com.csquanta.streamline.Controllers;

import com.csquanta.streamline.CountDown;
import com.csquanta.streamline.PomodoroClock;
import com.csquanta.streamline.TimeMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TaskBlockController implements Initializable {
    private VBox breakContainer;
    private VBox tempTimerContainer;

    public VBox getTempTimerContainer() {
        return tempTimerContainer;
    }

    public void setTempTimerContainer(VBox tempTimerContainer) {
        this.tempTimerContainer = tempTimerContainer;
    }

    public VBox getBreakContainer() {
        return breakContainer;
    }

    public void setBreakContainer(VBox breakContainer) {
        this.breakContainer = breakContainer;
    }

    @FXML
    private Label dueDate;

    @FXML
    private HBox labelContainer;

    @FXML
    private Label numOfPomodoroSession;

    @FXML
    private Label taskTitle;

    @FXML
    private VBox timerContainer;

    public void setTimerContainer(VBox timerContainer) {
        this.timerContainer = timerContainer;
    }

// Timer


    @FXML
    private Label clockLabel;

    @FXML
    private ProgressBar clockProgressBar;

    @FXML
    private VBox container;
   public boolean finished;

    public VBox getContainer() {
        return container;
    }

    public void setContainer(VBox container) {
        this.container = container;
    }

    public static VBox mainContainerOfTimer;


    @FXML
    private Label sessionCount;
    @FXML
    private Button toggleBtn;


    @FXML
    void toggleBtnClicked(ActionEvent event) {
        if (countdown.isRunning())
            stop();     // stops the counter if it is already running
        else
            activate(); // activate the counter if it is not running
    }









    public VBox getTimerContainer() {
        return timerContainer;
    }

 @FXML
    private ImageView startImage;

    public HBox getLabelContainer() {
        return labelContainer;
    }

    public void setLabelContainer(HBox labelContainer) {
        this.labelContainer = labelContainer;
    }

    public Label getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate.setText(dueDate);
    }

    public Label getNumOfPomodoroSession() {
        return numOfPomodoroSession;
    }

    public void setNumOfPomodoroSession(String sessions) {
        this.numOfPomodoroSession.setText(sessions);
    }

    public Label getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle.setText(taskTitle);
    }

    @FXML
    void mouseEnteredinStarBox(MouseEvent event) {
//        Wobble wobble = new Wobble(this.);
//        wobble.play();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clock = new PomodoroClock(
                this, clockLabel, clockProgressBar, TimeMode.POMODORO);
        countdown = new CountDown(TimeMode.POMODORO, clock);
//        initializeButtonToMode();

    }






//    ........
   private CountDown countdown;
    private PomodoroClock clock;

    private int maxLoopsCounts;

    public int getMaxLoopsCounts() {
        return maxLoopsCounts;
    }

    public void setMaxLoopsCounts(int maxLoopsCounts) {
        this.maxLoopsCounts = maxLoopsCounts;
    }

    public int currentAutoLoop =1;
    private Map<Button, TimeMode> buttonToMode;


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
        countdown.reset();
        removeTimeIsUpStyles();
    }

    private void start() {
        System.out.println("max "+maxLoopsCounts);
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

    private void addingBreak(){
        FXMLScene breakScene;
        VBox root;

        {
            try {
                breakScene = FXMLScene.load("/Fxml/timer.fxml");
                root = (VBox) breakScene.root;
                TimerController timerController = (TimerController) breakScene.controller;
                timerController.setTempContainerOne(tempTimerContainer);
                timerController.setTempContainerTwo(breakContainer);
                System.out.println(tempTimerContainer);
                breakContainer.getChildren().clear();
                breakContainer.getChildren().setAll(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    private void removeTimeIsUpStyles() {


    }
    private void addTimeIsUpStyles() {
        addingBreak();
    }

    private void playSound() {
//        Media sound = new Media(this.getClass().getResource("/Sounds/sound.wav").toString());
//        MediaPlayer player = new MediaPlayer(sound);
//
//        player.play();
    }
    public static void breakFinished(Boolean b){

    }
}
