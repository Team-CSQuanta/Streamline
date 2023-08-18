package com.csquanta.streamline.Models;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class MyTimer implements Runnable{
    int min, sec;
    Label minutes, seconds;
    public Thread t;
    public MyTimer(int min, int sec, Label minL, Label secL){
        this.min = min;
        this.sec = sec;
        this.minutes = minL;
        this.seconds = secL;
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
                sec--;
                System.out.println(sec);
                timeFormatCorrection(seconds, sec);
                if (sec == 0) {
                    min--;
                    System.out.println(min);
                    timeFormatCorrection(minutes, min);
                    sec = 60;
                }
                if(min == 0){
                    seconds.setText("00");
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
