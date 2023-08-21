package com.csquanta.streamline.Models;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyTimer implements Runnable{
    int min, sec = 60;
    int sessionCounter;
    int totalSession;
    Button button;
    Label minutes, seconds;
    public Thread t;
    String session;
    public MyTimer(int min, Label minL, Label secL, Button button, String SessionName, int sessionCounter, int totalSession){
        this.min = min;
        this.sec = sec;
        this.minutes = minL;
        this.seconds = secL;
        this.button = button;
        this.session = SessionName;
        this.totalSession = totalSession;
        this.sessionCounter = sessionCounter;
        timeFormatCorrection(minutes, min);
        secL.setText(String.valueOf(sec));
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
                    timeFormatCorrection(seconds, sec);
                }

                if(sec == 59){
                    timeFormatCorrection(minutes, (min -1));
                }
                if (sec == 0) {
                    min--;
                    timeFormatCorrection(minutes, min);
                    if(!session.equals("Break"))
                        sessionCounter++;
                    sec = 60;
                }
                if(min == 0){
                    seconds.setText("00");

                }
                if(sessionCounter == totalSession && min == 0){
                    button.setText("Finish");
                }
                else if(session.equals("Session") && min == 0){
                    button.setText("Take Break");
                }else if (session.equals("Break") && min == 0) {
                    button.setText("Start session");
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
