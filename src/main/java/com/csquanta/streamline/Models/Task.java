package com.csquanta.streamline.Models;

import java.time.LocalDate;
import java.util.TreeSet;

public class Task {
    public static Task taskObject = new Task();
    private TreeSet<Task> tasksList = new TreeSet<>(new TaskComparator());
    public void addTask(Task task){
        tasksList.add(task);
    }
    public void removeTask(Task task){
        tasksList.remove(task);
    }
    private String taskTitle;
    private int numOfSessions;
    private LocalDate dueDate;
    private String priority;
    private String tag;
    private String description;

    public Task() {
    }

    public TreeSet<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(TreeSet<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public Task(String taskTitle, int numOfSessions, LocalDate dueDate, String priority, String tag, String description) {
        this.taskTitle = taskTitle;
        this.numOfSessions = numOfSessions;
        this.dueDate = dueDate;
        this.priority = priority;
        this.tag = tag;
        this.description = description;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public int getNumOfSessions() {
        return numOfSessions;
    }

    public void setNumOfSessions(int numOfSessions) {
        this.numOfSessions = numOfSessions;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
