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

        return compareEisenhowerCategories(o1, o2);
    }

    private int compareEisenhowerCategories(Task task1, Task task2) {
        boolean isUrgent1 = task1.isUrgent();
        boolean isImportant1 = task1.isImportant();
        boolean isUrgent2 = task2.isUrgent();
        boolean isImportant2 = task2.isImportant();

        if (isUrgent1 && isImportant1) {
            if (isUrgent2 && isImportant2) {
                return 0;
            } else {
                return -1;
            }
        } else if (isUrgent2 && isImportant2) {
            return 1;
        } else if (isUrgent1) {
            if (isUrgent2) {
                return 0;
            } else {
                return -1;
            }
        } else if (isUrgent2) {
            return 1;
        } else if (isImportant1) {
            return -1;
        } else if (isImportant2) {
            return 1;
        } else {
            return 0;
        }
    }

}
