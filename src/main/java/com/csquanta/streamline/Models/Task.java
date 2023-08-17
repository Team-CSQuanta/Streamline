package com.csquanta.streamline.Models;

import com.csquanta.streamline.Controllers.ShopController;
import javafx.scene.control.DatePicker;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Task implements Serializable {
    public static Task taskObject = new Task();

    private ArrayList<Task> tasksList = new ArrayList<>();
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
    private int taskID;

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public Task() {
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public void setTasksList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }



    public Task(String taskTitle, int numOfSessions, LocalDate dueDate, String priority, String tag, String description, int id) {
        this.taskTitle = taskTitle;
        this.numOfSessions = numOfSessions;
        this.dueDate = dueDate;
        this.priority = priority;
        this.tag = tag;
        this.description = description;
        this.taskID = id;
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


    public  String getFormattedDueDate() {
        String monthAbbreviation = dueDate.getMonth().name().substring(0, 3);
        int day = dueDate.getDayOfMonth();
        int year = dueDate.getYear();
        return String.format("%s %02d, %04d", monthAbbreviation, day, year);
    }
    public boolean isUrgent() {
        return priority.toLowerCase().contains("urgent") && !priority.toLowerCase().contains("not urgent");
    }

    public boolean isImportant() {
        return priority.toLowerCase().contains("important") && !priority.toLowerCase().contains("not important");
    }
    public static void serializeTasks(){
        try(ObjectOutputStream objOStream = new ObjectOutputStream(new FileOutputStream("Tasks_Info"))){
            Task task = taskObject;
            objOStream.writeObject(task);
        }catch (Exception e){
            System.out.println("Serialization failed");
        }
    }
    public static void deserializeTasks(){
        try(ObjectInputStream objIStream = new ObjectInputStream(new FileInputStream("Tasks_Info"))){
            taskObject = (Task) objIStream.readObject();

        }catch (Exception e){
            System.out.println("Deserialization failed");
            e.printStackTrace();
        }
    }
}
