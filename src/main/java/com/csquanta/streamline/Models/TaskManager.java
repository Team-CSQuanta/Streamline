package com.csquanta.streamline.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.csquanta.streamline.Controllers.TakeBreakController.restMode;
import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class TaskManager {
    private Timer timer;

    public void startTaskChecking() {
        timer = new Timer();
        long delay = 60000;
       // long period = 24 * 60 * 60 * 1000;
        long period = 60 * 1000;


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                LocalDate today = LocalDate.now();
                ArrayList<Task> incompleteTasks = new ArrayList<>();

                for (Task Todotask : Task.taskObject.getTasksList())
                {
                    if (Todotask.getDueDate().isEqual(today) && Todotask.isCompleted()) {
                        System.out.println("Task for today is completed: " + Todotask.getTaskTitle());

                    } else if (Todotask.getDueDate().isEqual(today)) {
                        incompleteTasks.add(Todotask);
                    }
                }
                LocalTime currentTime = LocalTime.now();
                LocalTime midnight = LocalTime.of(8, 0, 59); // 11:59:59 PM

                if (currentTime.isAfter(midnight)) {
                    if (!restMode) {
                        userInfo.deductHealthPointsBasedOnTasks(incompleteTasks);
                    }
                }
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
