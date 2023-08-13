package com.csquanta.streamline.Models;

import java.time.LocalDate;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        LocalDate date1 = o1.getDueDate();
        LocalDate date2 = o2.getDueDate();
        if(date1.isBefore(date2)){
            return -1;
        }
        else if(date1.isAfter(date2)){
            return 1;
        }
        return 0;
    }
}
