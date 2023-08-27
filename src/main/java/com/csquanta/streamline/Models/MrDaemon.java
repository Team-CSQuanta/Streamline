package com.csquanta.streamline.Models;

import com.csquanta.streamline.Networking.ChallengeInfoWhenParticipated;
import com.csquanta.streamline.Networking.MrMonsterWinner;
import javafx.application.Platform;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.csquanta.streamline.Controllers.ChallengeController.networkUtil;
import static com.csquanta.streamline.Controllers.TakeBreakController.restMode;
import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class MrDaemon {
    private Timer timer;

    public void startTaskChecking() {
        timer = new Timer();
        long delay = 60000;
        //long period = 24 * 60 * 60 * 1000;
        long period = 60 * 1000;


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                LocalDate today = LocalDate.now();
                ArrayList<Task> incompleteTasks = new ArrayList<>();
                ArrayList<Task> futureTasks = new ArrayList<>();
                for (Task Todotask : Task.taskObject.getTasksList())
                {
                    if (Todotask.getDueDate().isEqual(today) && Todotask.isCompleted()) {
                        System.out.println("Task for today is completed: " + Todotask.getTaskTitle());

                    } else if (Todotask.getDueDate().isEqual(today)) {
                        incompleteTasks.add(Todotask);
                    }else if(Todotask.getDueDate().isAfter(today)){
                        futureTasks.add(Todotask);
                    }
                }
                LocalTime currentTime = LocalTime.now();
                LocalTime midnight = LocalTime.of(0, 5, 59); // 11:59:59 PM

                if (currentTime.isAfter(midnight)) {
                    if (!userInfo.isRestMode()) {
                        userInfo.deductHealthPointsBasedOnTasks(incompleteTasks);
                    }
                }
                if(futureTasks.size() == 0){
                    ChallengeUI.challengeUI.deductHealthPointsBasedOnMonsterDamagePerAttack();
                }

                // Checking whether user's health is Zero or not
                if (userInfo.getUserHealth()==0){
                    Platform.runLater(() ->{
                        //TODO:  need to add code that will reload the scene
                        ChallengeUI.challengeUI.monsterAcquisition();
                        MrMonsterWinner mrMonsterWinner = new MrMonsterWinner(userInfo.getEmail(), ChallengeInfoWhenParticipated.challengeInfoWhenParticipated.getParticipantsEmail(), true, "userHealthZero");
                        try {
                            networkUtil.write(mrMonsterWinner);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }


//                if(incompleteTasks.size() == 0){
//                    userInfo.getConsistencyTracker().add(1);
//                }else{
//                    userInfo.getConsistencyTracker().add(0);
//                }
//                //TODO:  Check consistency
//                ConsistenceInformation consistenceInformation = ChallengeUI.challengeUI.consistencyChecker();
//
//                // If the challenge mode is on
//                if(!consistenceInformation.isConsistence()){
//                    // TODO: Add code that will deduct users health
//
//                }

            }
        };

        timer.scheduleAtFixedRate(task, delay, period);
    }

    public void stopTaskChecking() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
