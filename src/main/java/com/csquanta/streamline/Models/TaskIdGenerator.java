package com.csquanta.streamline.Models;

import java.io.*;

public class TaskIdGenerator implements Serializable {
    public static TaskIdGenerator taskIdGenerator = new TaskIdGenerator();
    private int taskID;

    public int getTaskID(){
        return ++taskID;
    }
    public void TaskIdGenerator(){
        taskID = 0;
    }
    public void decrementId(){
        taskID--;
    }
    public static void serializeTaskID(){
        try(ObjectOutputStream objOStream = new ObjectOutputStream(new FileOutputStream("TaskID"))){
            TaskIdGenerator id = taskIdGenerator;
            objOStream.writeObject(id);
        }catch (Exception e){
            System.out.println("Serialization failed");
        }
    }
    public static void deserializeTaskID(){
        try(ObjectInputStream objIStream = new ObjectInputStream(new FileInputStream("TaskID"))){
            taskIdGenerator = (TaskIdGenerator) objIStream.readObject();

        }catch (Exception e){
            System.out.println("Deserialization failed");
            e.printStackTrace();
        }
    }
}
