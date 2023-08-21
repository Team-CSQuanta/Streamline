package com.csquanta.streamline.Models;

import com.csquanta.streamline.Controllers.PomodoroPageController;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MyTimer implements Runnable{
    StackPane pomodoroPage;
    int min, sec = 60;
    int sessionCounter;
    int totalSession;
    Button button;
    Label minutes, seconds;
    public Thread t;
    String session;
    public MyTimer(int min, Label minL, Label secL, Button button, String SessionName, int sessionCounter, int totalSession, StackPane pomodoroPage){
        this.min = min;
        this.minutes = minL;
        this.seconds = secL;
        this.button = button;
        this.session = SessionName;
        this.totalSession = totalSession;
        this.sessionCounter = sessionCounter;
        this.pomodoroPage = pomodoroPage;
        timeFormatCorrection(minutes, min);
        t = new Thread(this, "Timer");
    }
    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Platform.runLater(() ->{
                if(min != 0){
                    sec--;
                    System.out.println("Seconds : " + sec);
                    timeFormatCorrection(seconds, sec);
                    if(sec == 59){
                        timeFormatCorrection(minutes, (min -1));
                    }
                    if (sec == 0) {
                        min--;
                        System.out.println("Minutes: " + min);
                        timeFormatCorrection(minutes, min);
                        sec = 60;
                    }
                }else{

                    System.out.println("Total Sessoin : " + totalSession);
                    seconds.setText("00");
                    if(session.equals("Session")&& sessionCounter != totalSession){
                        System.out.println("Session counter before: " + sessionCounter);
                        sessionCounter++;
                        System.out.println("Session counter after increment: "+ sessionCounter);
                        button.setText("Take Break");
                        if(sessionCounter != 1){
                            try {
                                PomodoroPageController.smallRewardAfterEachSession(pomodoroPage);
                            } catch (IOException e) {
                                throw new RuntimeException(e);

                            }
                        }
                    }
                    else if (session.equals("Break") && sessionCounter != totalSession) {
                        button.setText("Start session");
                    }
                    if(sessionCounter == totalSession){
                        button.setText("Finish");
                    }

                }



            });

        } while (min != 0);

    }
    private void timeFormatCorrection(Label label, int counter){
        if(counter == 0 || counter == 1 || counter == 2 || counter == 3 || counter == 4 || counter == 5 || counter == 6 || counter == 7 || counter == 8 || counter == 9){
            label.setText("0"+String.valueOf(counter));
        }
        else{
            label.setText(String.valueOf(counter));
        }
    }
}
