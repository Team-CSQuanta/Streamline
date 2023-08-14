package com.csquanta.streamline.Controllers;

import animatefx.animation.Wobble;
import com.csquanta.streamline.CountDown;
import com.csquanta.streamline.PomodoroClock;
import com.csquanta.streamline.TimeMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TaskBlockController implements Initializable {

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

    @FXML
    private Label clockLabel;

    @FXML
    private ProgressBar clockProgressBar;

    @FXML
    private VBox container;

    @FXML
    private Label sessionCount;
    @FXML
    private Button toggleBtn;

    @FXML
    private Label totalSession;

    private CountDown countdown;
    private PomodoroClock clock;

    private int maxLoopsCounts = 0;

    public int currentAutoLoop =1;
    private Map<Button, TimeMode> buttonToMode;
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

    public Label getTotalSession() {
        return totalSession;
    }

    public void setTotalSession(Label totalSession) {
        this.totalSession = totalSession;
    }

    @FXML
    void mouseEnteredinStarBox(MouseEvent event) {
//      Wobble wobble = new Wobble(this);
//      wobble.play();
    }

    @FXML
    void toggleBtnClicked(ActionEvent event) {
        if (countdown.isRunning())
            stop();     // stops the counter if it is already running
        else
            activate(); // activate the counter if it is not running
    }

    @FXML
    void start(MouseEvent event) {
        if(event.getSource() instanceof  VBox container){
            Parent parent = container.getParent();
            Label label = (Label) parent.lookup("#numOfPomodoroSession");
            System.out.println(label.getText());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clock = new PomodoroClock(
                this, clockLabel, clockProgressBar, TimeMode.POMODORO);
        countdown = new CountDown(TimeMode.POMODORO, clock);
//        initializeButtonToMode();
        System.out.println("max"+maxLoopsCounts);

    }



//    public void initialize() {
//        clock = new PomodoroClock(
//                this, clockLabel, clockProgressBar, TimeMode.POMODORO);
//        countdown = new CountDown(TimeMode.POMODORO, clock);
//        initializeButtonToMode();
//        System.out.println("max"+maxLoopsCounts);
//    }

//    private void initializeButtonToMode() {
//        buttonToMode = new HashMap<>();
//        buttonToMode.put(pomodoroBtn, TimeMode.POMODORO);
//
//    }



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
        System.out.println("max "+maxLoopsCounts);
        countdown.start();
        updateToggleBtn("Stop");
    }

    public void timeIsUp() {
        addTimeIsUpStyles();
        playSound();

        if ( currentAutoLoop < Integer.parseInt(this.getTotalSession().getText())){
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
