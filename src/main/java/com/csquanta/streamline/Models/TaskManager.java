package com.csquanta.streamline.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.csquanta.streamline.Models.UserInformation.userInfo;

public class TaskManager {
    private Timer timer;

    public void startTaskChecking() {
        timer = new Timer();
        long delay = 0;
       // long period = 24 * 60 * 60 * 1000;
        long period = 60 * 1000;


        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                LocalDate today = LocalDate.now();
                ArrayList<Task> incompleteTasks = new ArrayList<>();

                for (Task task : Task.taskObject.getTasksList()) {
                    if (task.getDueDate().isEqual(today) && task.isCompleted()) {
                        System.out.println("Task for today is completed: " + task.getTaskTitle());

                    } else if (task.getDueDate().isEqual(today)) {
                        incompleteTasks.add(task);
                    }
                }


                LocalTime currentTime = LocalTime.now();
                LocalTime midnight = LocalTime.of(20, 4, 59); // 11:59:59 PM

                if (currentTime.isAfter(midnight)) {
                    userInfo.deductHealthPointsBasedOnTasks(incompleteTasks);
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
